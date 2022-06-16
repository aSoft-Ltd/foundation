@file:JvmName("PendingUtilsJvm")
@file:Suppress("NOTHING_TO_INLINE")

package koncurrent

actual inline fun <T, R> Pending<T>.then(
    executor: Executor, noinline onResolved: ((T) -> R)
): Pending<R> = thenApplyAsync(onResolved, executor)

actual inline fun <T, R> Pending<T>.then(noinline onResolved: ((T) -> R)): Pending<R> = thenApplyAsync(onResolved)

actual inline fun <T> Pending<T>.catch(executor: Executor, noinline onRejected: (Throwable) -> T): Pending<T> = exceptionallyAsync(onRejected, executor)

actual inline fun <T> Pending<T>.catch(noinline onRejected: (Throwable) -> T): Pending<T> = exceptionally(onRejected)

actual inline fun <T> Pending<T>.complete(executor: Executor, noinline finalizer: (Settled<T>) -> Unit) = whenCompleteAsync({ obj, err ->
    when {
        err != null && obj != null -> finalizer(Fulfilled(obj))
        err != null && obj == null -> finalizer(Rejected(err))
        err == null && obj != null -> finalizer(Fulfilled(obj))
        else /* err==null && obj==null */ -> finalizer(Rejected(IllegalStateException("Completed without an error nor an object")))
    }
}, executor)

actual inline fun <T> Pending<T>.complete(noinline finalizer: (Settled<T>) -> Unit) = whenCompleteAsync { obj, err ->
    when {
        err != null && obj != null -> finalizer(Fulfilled(obj))
        err != null && obj == null -> finalizer(Rejected(err))
        err == null && obj != null -> finalizer(Fulfilled(obj))
        else /* err==null && obj==null */ -> finalizer(Rejected(IllegalStateException("Completed without an error nor an object")))
    }
}

actual inline fun <T> Pending<T>.finally(executor: Executor, noinline finalizer: () -> Unit): Pending<T> = whenCompleteAsync({ _, _ -> finalizer() }, executor)

actual inline fun <T> Pending<T>.finally(noinline finalizer: () -> Unit): Pending<T> = whenComplete { _, _ -> finalizer() }

actual inline fun <T> Pending<T>.resolveWith(value: T) = complete(value)

actual inline fun <T> Pending<T>.rejectWith(exception: Throwable) = completeExceptionally(exception)