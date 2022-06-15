@file:JvmName("PendingUtilsCommon")

package koncurrent

import kotlin.jvm.JvmName

expect fun <T, R> Pending<T>.then(executor: Executor, onResolved: ((T) -> R), onRejected: ((Throwable) -> R)? = null): Pending<R>

expect fun <T, R> Pending<T>.then(onResolved: ((T) -> R), onRejected: ((Throwable) -> R)? = null): Pending<R>

fun <T, R> Pending<T>.then(onResolved: (T) -> R): Pending<R> = then(onResolved, null)

expect fun <T> Pending<T>.catch(onRejected: (Throwable) -> T): Pending<T>

expect fun <T> Pending<T>.catch(executor: Executor, onRejected: (Throwable) -> T): Pending<T>

expect fun <T> Pending<T>.finally(executor: Executor, finalizer: () -> Unit): Pending<T>

expect fun <T> Pending<T>.finally(finalizer: () -> Unit): Pending<T>