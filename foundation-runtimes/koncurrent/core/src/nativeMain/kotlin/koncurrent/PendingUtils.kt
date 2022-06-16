package koncurrent

actual inline fun <T, R> Pending<T>.then(executor: Executor, noinline onResolved: ((T) -> R)) = then(executor, onResolved = onResolved, onRejected = null)

actual inline fun <T, R> Pending<T>.then(noinline onResolved: ((T) -> R)) = then(executor, onResolved = onResolved, onRejected = null)

actual inline fun <T> Pending<T>.catch(executor: Executor, noinline onRejected: (Throwable) -> T): Pending<T> = error(executor, onRejected)

actual inline fun <T> Pending<T>.catch(noinline onRejected: (Throwable) -> T): Pending<T> = error(onRejected)

actual inline fun <T> Pending<T>.complete(executor: Executor, noinline finalizer: (Settled<T>) -> Unit): Pending<T> = complete(executor) {
    finalizer(it)
}

actual inline fun <T> Pending<T>.complete(noinline finalizer: (Settled<T>) -> Unit) = complete { finalizer(it) }

actual inline fun <T> Pending<T>.finally(executor: Executor, noinline finalizer: () -> Unit): Pending<T> = complete(executor) { finalizer() }

actual inline fun <T> Pending<T>.finally(noinline finalizer: () -> Unit): Pending<T> = complete { finalizer() }

actual inline fun <T> Pending<T>.resolveWith(value: T) = resolveWith(value)

actual inline fun <T> Pending<T>.rejectWith(exception: Throwable) = rejectWith(exception)