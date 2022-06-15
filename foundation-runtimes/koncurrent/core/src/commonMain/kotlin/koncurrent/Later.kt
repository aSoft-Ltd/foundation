@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package koncurrent

import koncurrent.LaterState.PENDING
import koncurrent.LaterState.Settled
import koncurrent.LaterState.Settled.FULFILLED
import koncurrent.LaterState.Settled.REJECTED
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

class Later<T>(val executor: Executor = Executors.default(), handler: ((resolve: (T) -> Unit, reject: ((Throwable) -> Unit)) -> Unit)? = null) {

    @JsName("_ignore_fromHandler")
    constructor(executor: Executor = Executors.default(), handler: LaterHandler<T>) : this(executor, { resolve, reject -> handler.execute(resolve, reject) })

    private val thenQueue = mutableListOf<LaterQueueComponent<*>>()
    private val finallyQueue = mutableListOf<LaterQueueComponent<*>>()

    private var state: LaterState<T> = PENDING()

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
        fun <T> resolve(value: T, executor: Executor = Executors.default()): Later<T> {
            val l = Later<T>(executor)
            l.resolveWith(value)
            return l
        }

        @JvmStatic
        @JvmOverloads
        fun reject(error: Throwable, executor: Executor = Executors.default()): Later<Nothing> {
            val l = Later<Nothing>(executor)
            l.rejectWith(error)
            return l
        }
    }

    @JvmSynthetic
    fun <S> then(executor: Executor = this.executor, onResolved: ((T) -> S)?, onRejected: ((Throwable) -> S)? = null): Later<S> {
        val controlledLater = Later<S>(executor)
        thenQueue.add(LaterQueueComponent(controlledLater, onResolved as? (Any?) -> S, onRejected))
        when (val s = this.state) {
            is FULFILLED -> propagateFulfilled(s.value)
            is REJECTED -> propagateRejected(s.cause)
        }
        return controlledLater
    }

    @JvmSynthetic
    fun error(executor: Executor = this.executor, handler: (Throwable) -> T) = then(executor, null, handler)

    @JvmSynthetic
    fun catch(executor: Executor = this.executor, handler: (Throwable) -> T) = error(executor, handler)

    @JvmOverloads
    @JsName("_ignore_then")
    fun <S> then(onResolve: Function<@UnsafeVariance T, S>, executor: Executor = this.executor): Later<S> = then(
        executor = executor,
        onResolved = { value -> onResolve.apply(value) },
        onRejected = null
    )

    @JvmOverloads
    @JsName("_ignore_thenResolveOrReject")
    fun <S> then(onResolve: Function<@UnsafeVariance T, S>, onReject: Function<Throwable, S>, executor: Executor = this.executor): Later<S> = then(
        executor = executor,
        onResolved = { value -> onResolve.apply(value) },
        onRejected = { error -> onReject.apply(error) }
    )

    /**
     * Same as calling catch on javascript or kotlin
     */
    @JsName("_ignore_error")
    @JvmOverloads
    fun <T> error(handler: Function<Throwable, T>, executor: Executor = this.executor): Later<T> = then(
        executor = executor,
        onResolved = null,
        onRejected = { err -> handler.apply(err) }
    )

    /**
     * Same as calling finally on javascript or kotlin
     */
    @JsName("_ignore_complete")
    fun complete(handler: Fun<Settled<@UnsafeVariance T>>, executor: Executor = this.executor) = complete(executor, cleanUp = { state -> handler.invoke(state) })

    private fun cleanUp(executor: Executor, cleanUp: (state: Settled<T>) -> Any?): Later<T> {
        val s = this.state
        if (s is Settled) {
            cleanUp(s)
            return when (s) {
                is FULFILLED -> Later.resolve(s.value, executor)
                is REJECTED -> Later.reject(s.cause, executor) as Later<T>
            }
        }

        val controlledLater = Later<T>(executor)
        val resolve = { value: T ->
            cleanUp(FULFILLED(value))
            value
        }
        val rejected = { err: Throwable ->
            cleanUp(REJECTED(err))
            err as T
        }
        finallyQueue.add(LaterQueueComponent(controlledLater, resolve as? (Any?) -> T, rejected))
        return controlledLater
    }

    @JvmSynthetic
    fun complete(executor: Executor = this.executor, cleanUp: (state: Settled<T>) -> Any?) = cleanUp(executor, cleanUp)

    @JvmSynthetic
    fun finally(executor: Executor = this.executor, cleanUp: (state: Settled<T>) -> Any?) = cleanUp(executor, cleanUp)

    @JvmName("toCompletableFuture")
    @JsName("toPromise")
    fun toPending(): Pending<T> = toNativeImplementation()

    fun resolveWith(value: @UnsafeVariance T) {
        if (this.state is PENDING) {
            try {
                this.state = FULFILLED(value as T)
                propagateFulfilled(value)
            } catch (err: Throwable) {
                rejectWith(err)
            }
        }
    }

    fun rejectWith(error: Throwable) {
        if (this.state is PENDING) {
            this.state = REJECTED(error)
            propagateRejected(error)
        }
    }

    private fun propagateFulfilled(value: Any?) {
        thenQueue.forEach {
            val controlledLater = it.controlledLater as Later<Any?>
            val fulfilledFn = it.fulfilledFn
            try {
                val valueOrLater =
                    fulfilledFn?.invoke(value) ?: throw RuntimeException("No fulfilled function provided")
                when {
                    valueOrLater.isThenable() -> valueOrLater.then(
                        executor = executor,
                        onResolved = { v -> controlledLater.resolveWith(v) },
                        onRejected = { error -> controlledLater.rejectWith(error) }
                    )
                    else -> controlledLater.resolveWith(valueOrLater)
                }
            } catch (err: Throwable) {
                controlledLater.resolveWith(value)
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
                when {
                    valueOrLater.isThenable() -> valueOrLater.then(
                        executor = executor,
                        onResolved = { v -> controlledLater.resolveWith(v) },
                        onRejected = { err -> controlledLater.rejectWith(err) }
                    )
                    else -> controlledLater.resolveWith(valueOrLater)
                }
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
}
