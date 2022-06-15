package koncurrent

actual fun <T, R> Pending<T>.then(
    executor: Executor,
    onResolved: ((T) -> R), onRejected: ((Throwable) -> R)?
) = then(onResolved = onResolved, onRejected = onRejected)

actual fun <T, R> Pending<T>.then(
    onResolved: ((T) -> R), onRejected: ((Throwable) -> R)?
) = then(onResolved = onResolved, onRejected = onRejected)

actual fun <T> Pending<T>.catch(executor: Executor, onRejected: (Throwable) -> T): Pending<T> = error(executor, onRejected)

actual fun <T> Pending<T>.catch(onRejected: (Throwable) -> T): Pending<T> = error(onRejected)

actual fun <T> Pending<T>.finally(executor: Executor, finalizer: () -> Unit): Pending<T> = complete(executor) { finalizer() }

actual fun <T> Pending<T>.finally(finalizer: () -> Unit): Pending<T> = complete { finalizer() }