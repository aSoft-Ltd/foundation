@file:Suppress("PackageDirectoryMismatch")

package koncurrent.later

import koncurrent.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T> Later<T>.await() = suspendCancellableCoroutine { cont: Continuation<T> ->
    complete { state ->
        when (state) {
            is Fulfilled -> cont.resume(state.value)
            is Rejected -> cont.resumeWithException(state.cause)
        }
    }
}