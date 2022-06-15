package koncurrent

actual fun <T, R> Pending<T>.then(
    onResolved: ((T) -> R), onRejected: ((Throwable) -> R)?
) = then(onFulfilled = onResolved, onRejected = onRejected)

actual fun <T, R> Pending<T>.then(
    executor: Executor,
    onResolved: ((T) -> R), onRejected: ((Throwable) -> R)?
) = then(onFulfilled = onResolved, onRejected = onRejected)