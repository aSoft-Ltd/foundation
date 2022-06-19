package koncurrent

import koncurrent.pending.internal.rejectFn
import koncurrent.pending.internal.resolveFn
import koncurrent.pending.internal.state

actual inline fun <T> Executor.pending(noinline block: () -> T): Pending<T> = Promise<T> { resolve, reject ->
    try {
        resolve(block())
    } catch (err: Throwable) {
        reject(err)
    }
}

actual inline fun <T> pending(noinline block: () -> T): Pending<T> = Promise { resolve, reject ->
    try {
        resolve(block())
    } catch (err: Throwable) {
        reject(err)
    }
}

actual inline fun <T> ControlledPending(): Pending<T> {
    var resolveFn: ((T) -> Unit)? = null
    var rejectFn: ((Throwable) -> Unit)? = null
    val promise = Promise<T> { resolve, reject ->
        resolveFn = resolve
        rejectFn = reject
    }
    promise.resolveFn = resolveFn
    promise.rejectFn = rejectFn
    promise.state = PendingState
    return promise
}