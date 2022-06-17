@file:JvmName("PendingUtilsCommon")

package koncurrent

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

inline fun <T, R> Pending<Pending<T>>.flatMap(noinline onFulfilled: (T) -> R): Pending<R> {
    val pending = ControlledPending<R>()
    then(onResolved = { p ->
        p.then {
            try {
                pending.resolveWith(onFulfilled(it))
            } catch (err: Throwable) {
                pending.rejectWith(err)
            }
        }
    })
    return pending
}