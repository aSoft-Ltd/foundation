@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package later

import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.suspendCancellableCoroutine
import later.LaterState.Settled.FULFILLED
import later.LaterState.Settled.REJECTED
import later.LaterState.Settled
import later.LaterState.PENDING
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

class Later<out T> internal constructor(executor: ((resolve: (T) -> Unit, reject: ((Throwable) -> Unit)) -> Unit)? = null) {

    @JsName("fromExecutor")
    constructor(executor: LaterExecutor<T>) : this({ resolve, reject -> executor.execute(resolve, reject) })

    private val thenQueue = mutableListOf<LaterQueueComponent<*>>()
    private val finallyQueue = mutableListOf<LaterQueueComponent<*>>()

    private val innerState: AtomicRef<LaterState<T>> = atomic(PENDING())

    internal val state get() = innerState.value

    init {
        loadToNextEventLoop {
            try {
                executor?.invoke(::resolveWith, ::rejectWith)
            } catch (err: Throwable) {
                rejectWith(err)
            }
        }
    }

    companion object {
        @JvmStatic
        fun <T> resolve(value: T): Later<T> {
            val l = Later<T>()
            l.resolveWith(value)
            return l
        }

        @JvmStatic
        fun reject(error: Throwable): Later<Nothing> {
            val l = Later<Nothing>()
            l.rejectWith(error)
            return l
        }
    }

    private fun <T> AtomicRef<List<T>>.clear() {
        lazySet(listOf())
    }

    @JvmSynthetic
    fun <S> then(onResolved: ((T) -> S)?, onRejected: ((Throwable) -> S)? = null): Later<S> {
        val controlledLater = Later<S>()
        thenQueue.add(LaterQueueComponent(controlledLater, onResolved as? (Any?) -> S, onRejected))
        when (val s = state) {
            is FULFILLED -> propagateFulfilled(s.value)
            is REJECTED -> propagateRejected(s.cause)
        }
        return controlledLater
    }

    @JvmSynthetic
    fun <S> error(handler: (Throwable) -> S) = then(null, handler)

    @JvmSynthetic
    fun <S> catch(handler: (Throwable) -> S) = error(handler)

    @JsName("_ignore_then")
    fun <S> then(onResolve: Function<@UnsafeVariance T, S>): Later<S> = then(
        onResolved = { value -> onResolve.apply(value) },
        onRejected = null
    )

    @JsName("_ignore_thenResolveOrReject")
    fun <S> then(onResolve: Function<@UnsafeVariance T, S>, onReject: Function<Throwable, S>): Later<S> = then(
        onResolved = { value -> onResolve.apply(value) },
        onRejected = { error -> onReject.apply(error) }
    )

    /**
     * Same as calling catch on javascript or kotlin
     */
    @JsName("_ignore_error")
    fun <S> error(handler: Function<Throwable, S>): Later<S> = then(
        onResolved = null,
        onRejected = { err -> handler.apply(err) }
    )

    /**
     * Same as calling finally on javascript or kotlin
     */
    @JsName("_ignore_complete")
    fun complete(handler: Fun<Settled<@UnsafeVariance T>>) = complete(cleanUp = { state -> handler.invoke(state) })

    private fun cleanUp(cleanUp: (state: Settled<T>) -> Any?): Later<T> {
        val s = state
        if (s is Settled) {
            cleanUp(s)
            return when (s) {
                is FULFILLED -> Later.resolve(s.value)
                is REJECTED -> Later.reject(s.cause)
            }
        }

        val controlledLater = Later<T>()
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
    fun complete(cleanUp: (state: Settled<T>) -> Any?) = cleanUp(cleanUp)

    @JvmSynthetic
    fun finally(cleanUp: (state: Settled<T>) -> Any?) = cleanUp(cleanUp)

    fun resolveWith(value: @UnsafeVariance T) {
        if (state is PENDING) {
            try {
                innerState.lazySet(FULFILLED(value as T))
                propagateFulfilled(value)
            } catch (err: Throwable) {
                rejectWith(err)
            }
        }
    }

    fun rejectWith(error: Throwable) {
        if (state is PENDING) {
            innerState.lazySet(REJECTED(error))
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
