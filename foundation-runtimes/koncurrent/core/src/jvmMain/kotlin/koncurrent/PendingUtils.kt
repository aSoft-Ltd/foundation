@file:JvmName("PendingUtilsJvm")

package koncurrent

actual fun <T, R> Pending<T>.then(
    executor: Executor, onResolved: ((T) -> R)
): Pending<R> = thenApplyAsync(onResolved, executor)

actual fun <T, R> Pending<T>.then(onResolved: ((T) -> R)): Pending<R> = thenApplyAsync(onResolved)

actual fun <T> Pending<T>.catch(executor: Executor, onRejected: (Throwable) -> T): Pending<T> = exceptionallyAsync(onRejected, executor)

actual fun <T> Pending<T>.catch(onRejected: (Throwable) -> T): Pending<T> = exceptionally(onRejected)

actual fun <T> Pending<T>.finally(executor: Executor, finalizer: () -> Unit): Pending<T> = whenCompleteAsync({ _, _ -> finalizer() }, executor)

actual fun <T> Pending<T>.finally(finalizer: () -> Unit): Pending<T> = whenComplete { _, _ -> finalizer() }