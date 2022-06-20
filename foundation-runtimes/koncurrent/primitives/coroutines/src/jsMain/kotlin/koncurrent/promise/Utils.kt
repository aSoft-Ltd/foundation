package koncurrent.promise

import koncurrent.Promise
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


internal inline val Any.isPromise get() : Boolean = asDynamic()?.constructor?.name == "Promise"
suspend fun <T> Promise<T>.await(): T {
    return if (isPromise) suspendCancellableCoroutine { cont ->
        then(
            onFulfilled = { cont.resume(it) },
            onRejected = { cont.resumeWithException(it) }
        )
    } else {
        return unsafeCast<T>()
    }
}