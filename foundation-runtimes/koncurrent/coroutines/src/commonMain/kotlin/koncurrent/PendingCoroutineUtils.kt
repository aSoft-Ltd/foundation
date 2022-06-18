@file:Suppress("PackageDirectoryMismatch")

package koncurrent.coroutines

import koncurrent.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


//@PublishedApi
//internal suspend inline fun <reified T> Pending<T>.awaitCompletion(): T = suspendCancellableCoroutine { cont: Continuation<T> ->
//    if (this is T) cont.resume(this) else complete { state ->
//        when (state) {
//            is Fulfilled -> cont.resume(state.value)
//            is Rejected -> cont.resumeWithException(state.cause)
//        }
//    }
//}

@PublishedApi
internal suspend inline fun <reified T> Pending<T>.awaitCompletion(): T = suspendCancellableCoroutine { cont: Continuation<T> ->
    if (this is T) cont.resume(this) else complete { state ->
        when (state) {
            is Fulfilled -> when (val value = state.value) {
                is Pending<*> -> complete { state2 ->
                    when (state2) {
                        is Fulfilled -> cont.resume(state2.value)
                        is Rejected -> cont.resumeWithException(state2.cause)
                    }
                }
                else -> cont.resume(value)
            }
            is Rejected -> cont.resumeWithException(state.cause)
        }
    }
}

suspend inline fun <reified T> Pending<T>.await(): T = awaitCompletion()

//suspend inline fun <reified T> Pending<T>.await(): T {
//    var res = awaitCompletion()
//    while (res is Pending<*>) {
//        val r = (res as Pending<T>)
//        res = r.awaitCompletion()
//    }
//    return res
//}

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