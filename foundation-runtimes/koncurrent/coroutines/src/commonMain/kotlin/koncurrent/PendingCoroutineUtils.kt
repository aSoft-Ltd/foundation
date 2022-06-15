package koncurrent

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T> Pending<T>.await() = suspendCancellableCoroutine<T> { cont: Continuation<T> ->
    catch { err -> cont.resumeWithException(err) }.then { cont.resume(it) }
}