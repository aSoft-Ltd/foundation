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

suspend fun <T, R> Later<T>.map(transform: suspend (T) -> R): Later<R> {
    val later = Later<R>()
    try {
        later.resolveWith(transform(await()))
    } catch (err: Throwable) {
        later.rejectWith(err)
    }
    return later
}

suspend fun <T> Later<T>.catch(transform: suspend (Throwable) -> T): Later<T> {
    val later = Later<T>()
    try {
        later.resolveWith(await())
    } catch (err: Throwable) {
        try {
            later.resolveWith(transform(err))
        } catch (err: Throwable) {
            later.rejectWith(err)
        }
    }
    return later
}

suspend fun <T> Later<T>.collect(collector: suspend (T) -> Unit) = collector(await())