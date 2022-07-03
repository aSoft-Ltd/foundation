@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package koncurrent

import functions.Consumer
import functions.Function
import koncurrent.later.internal.LaterHandler
import koncurrent.later.internal.LaterQueueComponent
import koncurrent.later.internal.PlatformConcurrentMonad
import koncurrent.later.internal.toPlatformConcurrentMonad
import kotlinx.collections.atomic.mutableAtomicListOf
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic
import kotlin.reflect.typeOf

class Later<T>(handler: ((resolve: (T) -> Unit, reject: ((Throwable) -> Unit)) -> Unit)? = null, val executor: Executor = Executors.default()) {

    @JsName("_ignore_fromHandler")
    @JvmOverloads
    constructor(handler: LaterHandler<T>, executor: Executor = Executors.default()) : this({ resolve, reject -> handler.execute(resolve, reject) }, executor)

    private val thenQueue = mutableAtomicListOf<LaterQueueComponent<*>>()
    private val finallyQueue = mutableAtomicListOf<LaterQueueComponent<*>>()

    @JvmSynthetic
    @JsName("_ignore_state")
    var state: ConcurrentState<T> = PendingState

    init {
        executor.execute {
            try {
                handler?.invoke(::resolveWith, ::rejectWith)
            } catch (err: Throwable) {
                rejectWith(err)
            }
        }
    }

    companion object {
        @JvmStatic
        @JvmOverloads
        fun <T> resolve(value: T, executor: Executor = Executors.default()): Later<out T> {
            val l = Later<T>(executor = executor)
            l.resolveWith(value)
            return l
        }

        @JvmStatic
        @JvmOverloads
        fun reject(error: Throwable, executor: Executor = Executors.default()): Later<out Nothing> {
            val l = Later<Nothing>(executor = executor)
            l.rejectWith(error)
            return l
        }
    }

    /**
     * Schedules a code block to be executed after this [Later] resolves
     * This Method should be called from Kotlin only
     */
    @JvmSynthetic
    @JsName("_ignore_thenWithExecutor")
    fun <R> then(onResolved: ((T) -> R)?, onRejected: ((Throwable) -> R)? = null, executor: Executor = this.executor): Later<out R> {
        val controlledLater = Later<R>(executor = executor)
        thenQueue.add(LaterQueueComponent(controlledLater, onResolved as? (Any?) -> R, onRejected))
        when (val s = state) {
            is Fulfilled -> propagateFulfilled(s.value)
            is Rejected -> propagateRejected(s.cause)
            else -> {}
        }
        return controlledLater
    }

    /**
     * Schedules a code block to be executed after this [Later] resolves
     * This Method should be called from Javascript
     */
    @JvmSynthetic
    fun <S> then(onResolved: (T) -> S, executor: Executor = this.executor): Later<out S> = then(onResolved, null, executor)

    /**
     * Schedules a code block to be executed after this [Later] resolves
     * This Method should be called from java
     */
    @JvmOverloads
    @JsName("_ignore_then")
    fun <S> then(onResolved: Function<T, S>, executor: Executor = this.executor): Later<out S> = then(
        executor = executor,
        onResolved = { value -> onResolved.invoke(value) },
        onRejected = null
    )

    fun <S> flatten(onResolved: (T) -> Later<out S>, executor: Executor = this.executor): Later<out S> = when (val s = state) {
        is Fulfilled -> try {
            onResolved(s.value)
        } catch (err: Throwable) {
            Later.reject(err, executor)
        }
        is Rejected -> Later.reject(s.cause, executor)
        else -> {
            val later = Later<S>(executor = executor)
            then(
                executor = executor,
                onResolved = { res ->
                    onResolved(res).then(
                        executor = executor,
                        onResolved = { later.resolveWith(it) },
                        onRejected = { later.rejectWith(it) }
                    )
                },
                onRejected = { later.rejectWith(it) }
            )
            later
        }
    }

    @JvmOverloads
    @JsName("_ignore_flatten")
    fun <R> flatten(onResolved: Function<T, Later<out R>>, executor: Executor = this.executor) = flatten(onResolved::invoke, executor)

    fun error(handler: (Throwable) -> T, executor: Executor = this.executor): Later<out T> = then(null, handler, executor)

    /**
     * Same as calling catch on javascript or kotlin
     */
    @JsName("_ignore_error")
    @JvmOverloads
    fun <T> error(handler: Function<Throwable, T>, executor: Executor = this.executor): Later<out T> = then(
        executor = executor,
        onResolved = null,
        onRejected = { err -> handler.invoke(err) }
    )

    /**
     * Same as calling finally on javascript or kotlin
     */
    @JsName("_ignore_complete")
    @JvmOverloads
    fun complete(handler: Consumer<in Settled<T>>, executor: Executor = this.executor) = cleanUp(executor, cleanUp = { state -> handler.accept(state) })

    @JvmSynthetic
    fun complete(cleanUp: (state: Settled<T>) -> Any?, executor: Executor = this.executor) = cleanUp(executor, cleanUp)

    internal fun cleanUp(executor: Executor, cleanUp: (state: Settled<T>) -> Any?): Later<out T> {
        val s = this.state
        if (s is Settled) {
            cleanUp(s)
            return when (s) {
                is Fulfilled -> Later.resolve(s.value, executor) as Later<T>
                is Rejected -> Later.reject(s.cause, executor) as Later<T>
            }
        }

        val controlledLater = Later<T>(executor = executor)
        val resolve = { value: T ->
            cleanUp(Fulfilled(value))
            value
        }
        val rejected = { err: Throwable ->
            cleanUp(Rejected(err))
            err as T
        }
        finallyQueue.add(LaterQueueComponent(controlledLater, resolve as? (Any?) -> T, rejected))
        return controlledLater
    }

    @JvmName("toCompletableFuture")
    @JsName("toPromise")
    fun toPending(): PlatformConcurrentMonad<out T> = toPlatformConcurrentMonad()

    @JvmName("toCompletableFuture")
    @JsName("_ignore_toPromise")
    fun toPending(executor: Executor): PlatformConcurrentMonad<out T> = toPlatformConcurrentMonad(executor)

    fun resolveWith(value: @UnsafeVariance T): Boolean {
        if (state is PendingState) {
            try {
                state = Fulfilled(value)
                propagateFulfilled(value)
            } catch (err: Throwable) {
                rejectWith(err)
            }
            return true
        }
        return false
    }

    fun rejectWith(error: Throwable): Boolean {
        if (this.state is PendingState) {
            this.state = Rejected(error)
            propagateRejected(error)
            return true
        }
        return false
    }

    private fun propagateFulfilled(value: Any?) {
        thenQueue.forEach {
            val controlledLater = it.controlledLater as Later<Any?>
            val fulfilledFn = it.fulfilledFn
            try {
                val valueOrLater =
                    fulfilledFn?.invoke(value) ?: throw RuntimeException("No fulfilled function provided")
//                when {
//                    valueOrLater.isThenable() -> valueOrLater.then(
//                        executor = executor,
//                        onResolved = { v -> controlledLater.resolveWith(v) },
//                        onRejected = { error -> controlledLater.rejectWith(error) }
//                    )
//                    else -> controlledLater.resolveWith(valueOrLater)
//                }
                controlledLater.resolveWith(valueOrLater)
            } catch (err: Throwable) {
//                controlledLater.resolveWith(value)
                propagateRejected(err)
            }
        }

        finallyQueue.forEach {
            val controlledLater = it.controlledLater as Later<Any?>
            val fulfilledFn = it.fulfilledFn
            fulfilledFn?.invoke(value)
            controlledLater.resolveWith(value)
        }
        thenQueue.clear()
        finallyQueue.clear()
    }

    private fun propagateRejected(error: Throwable) {
        thenQueue.forEach {
            val controlledLater = it.controlledLater as Later<Any?>
            try {
                val rejectedFn = it.rejectedFn ?: throw RuntimeException("No catch function")
                val valueOrLater = rejectedFn(error)
//                when {
//                    valueOrLater.isThenable() -> valueOrLater.then(
//                        executor = executor,
//                        onResolved = { v -> controlledLater.resolveWith(v) },
//                        onRejected = { err -> controlledLater.rejectWith(err) }
//                    )
//                    else -> controlledLater.resolveWith(valueOrLater)
//                }
                controlledLater.resolveWith(valueOrLater)
            } catch (err: Throwable) {
                controlledLater.rejectWith(err)
            }
        }

        finallyQueue.forEach {
            val controlledLater = it.controlledLater
            val rejectedFn = it.rejectedFn
            rejectedFn?.invoke(error)
            controlledLater.rejectWith(error)
        }
        thenQueue.clear()
        finallyQueue.clear()
    }

    override fun toString(): String = "Later($state)"
}
