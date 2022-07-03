package koncurrent.pending

import koncurrent.Executor
import koncurrent.Later
import koncurrent.Pending
import koncurrent.Settled
import koncurrent.later.flatten as laterUnwrap

actual inline fun <T, R> Pending<T>.then(
    executor: Executor, noinline onResolved: ((T) -> R)
) = then(executor = executor, onResolved = onResolved, onRejected = null) as Later<R>

actual inline fun <T, R> Pending<T>.then(
    noinline onResolved: ((T) -> R)
) = then(executor = executor, onResolved = onResolved, onRejected = null) as Later<R>

actual inline fun <T> Pending<T>.catch(
    executor: Executor, noinline onRejected: (Throwable) -> T
): Pending<T> = error(executor = executor, handler = onRejected) as Later<T>

actual inline fun <T> Pending<T>.catch(
    noinline onRejected: (Throwable) -> T
): Pending<T> = error(handler = onRejected) as Later<T>

actual inline fun <T> Pending<T>.complete(
    executor: Executor, noinline finalizer: (Settled<T>) -> Unit
): Pending<T> = complete(executor = executor, cleanUp = { finalizer(it) }) as Later<T>

actual inline fun <T> Pending<T>.complete(
    noinline finalizer: (Settled<T>) -> Unit
) = complete(cleanUp = { finalizer(it) }) as Later<T>

actual inline fun <T> Pending<T>.finally(executor: Executor, noinline finalizer: () -> Unit): Pending<T> = complete(executor) { finalizer() }

actual inline fun <T> Pending<T>.finally(noinline finalizer: () -> Unit): Pending<T> = complete { finalizer() }

actual inline fun <T> Pending<T>.resolveWith(value: T) = resolveWith(value)

actual inline fun <T> Pending<T>.rejectWith(exception: Throwable) = rejectWith(exception)

actual inline fun <T, R> Pending<T>.flatten(
    noinline onFulfilled: (T) -> Pending<R>
): Pending<R> = flatten(onFulfilled) as Later<R>