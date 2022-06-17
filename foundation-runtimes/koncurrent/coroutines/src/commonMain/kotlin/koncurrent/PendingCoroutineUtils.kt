@file:Suppress("PackageDirectoryMismatch")

package koncurrent.coroutines

import koncurrent.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend inline fun <reified T> Pending<T>.await(): T = suspendCancellableCoroutine { cont: Continuation<T> ->
    if (this is T) cont.resume(this) else complete { state ->
        when (state) {
            is Fulfilled -> cont.resume(state.value)
            is Rejected -> cont.resumeWithException(state.cause)
        }
    }
}

suspend inline fun <reified T, R> Pending<T>.map(transform: (T) -> R): Pending<R> {
    val pending = ControlledPending<R>()
    try {
        pending.resolveWith(transform(await()))
    } catch (err: Throwable) {
        pending.rejectWith(err)
    }
    return pending
}

suspend inline fun <reified T> Pending<T>.exception(transform: (Throwable) -> T): Pending<T> {
    val pending = ControlledPending<T>()
    try {
        pending.resolveWith(await())
    } catch (err: Throwable) {
        try {
            pending.resolveWith(transform(err))
        } catch (err: Throwable) {
            pending.rejectWith(err)
        }
    }
    return pending
}

suspend inline fun <reified T> Pending<T>.collect(collector: (T) -> Unit) = collector(await())

suspend inline fun <reified T, R> Pending<T>.collectTo(collector: (T) -> R): R = collector(await())