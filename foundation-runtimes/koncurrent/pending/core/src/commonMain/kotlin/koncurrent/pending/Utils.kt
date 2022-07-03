@file:JvmName("UtilsCommon")

package koncurrent.pending

import koncurrent.Executor
import koncurrent.Pending
import koncurrent.Settled
import kotlin.jvm.JvmName

expect inline fun <T, R> Pending<T>.then(executor: Executor, noinline onResolved: ((T) -> R)): Pending<R>

expect inline fun <T, R> Pending<T>.then(noinline onResolved: (T) -> R): Pending<R>

expect inline fun <T> Pending<T>.catch(noinline onRejected: (Throwable) -> T): Pending<T>

expect inline fun <T> Pending<T>.catch(executor: Executor, noinline onRejected: (Throwable) -> T): Pending<T>

expect inline fun <T> Pending<T>.complete(executor: Executor, noinline finalizer: (Settled<T>) -> Unit): Pending<T>

expect inline fun <T> Pending<T>.complete(noinline finalizer: (Settled<T>) -> Unit): Pending<T>

expect inline fun <T> Pending<T>.finally(executor: Executor, noinline finalizer: () -> Unit): Pending<T>

expect inline fun <T> Pending<T>.finally(noinline finalizer: () -> Unit): Pending<T>

expect inline fun <T> Pending<T>.resolveWith(value: T): Boolean

expect inline fun <T> Pending<T>.rejectWith(exception: Throwable): Boolean

expect inline fun <T, R> Pending<T>.flatten(noinline onFulfilled: (T) -> Pending<R>): Pending<R>

inline fun <T> Pending<Pending<T>>.flatten(): Pending<T> = flatten { it }