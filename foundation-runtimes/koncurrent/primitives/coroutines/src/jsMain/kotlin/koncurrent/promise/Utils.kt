package koncurrent.promise

import koncurrent.Promise
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend inline fun <T> Promise<T>.await(): T = suspendCancellableCoroutine { cont ->
    then(
        onFulfilled = { cont.resume(it) },
        onRejected = { cont.resumeWithException(it) }
    )
}