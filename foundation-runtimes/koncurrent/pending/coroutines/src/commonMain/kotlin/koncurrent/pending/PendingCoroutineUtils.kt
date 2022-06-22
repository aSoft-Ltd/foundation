@file:JvmName("PendingCoroutineUtilsCommon")

package koncurrent.pending

import koncurrent.*
import kotlin.jvm.JvmName

expect suspend fun <T> Pending<T>.await(): T

expect suspend inline fun <T> Pending<Pending<T>>.awaitChain(): T

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