package koncurrent

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T> Pending<T>.await() = suspendCancellableCoroutine { cont: Continuation<T> ->
    complete { state ->
        when (state) {
            is Fulfilled -> cont.resume(state.value)
            is Rejected -> cont.resumeWithException(state.cause)
        }
    }
}

suspend fun <T, R> Pending<T>.map(transform: suspend (T) -> R): Pending<R> {
    val pending = ControlledPending<R>()
    try {
        pending.resolveWith(transform(await()))
    } catch (err: Throwable) {
        pending.rejectWith(err)
    }
    return pending
}

suspend fun <T> Pending<T>.collect(collector: suspend (T) -> Unit) = collector(await())