package koncurrent

actual fun <T, R> Pending<T>.then(
    executor: Executor,
    onResolved: ((T) -> R), onRejected: ((Throwable) -> R)?
): Pending<R> = handleAsync({ obj, err ->
    if (err != null) onRejected?.invoke(err) else onResolved(obj)
}, executor)

actual fun <T, R> Pending<T>.then(
    onResolved: ((T) -> R), onRejected: ((Throwable) -> R)?
): Pending<R> = handleAsync { obj, err -> if (err != null) onRejected?.invoke(err) else onResolved(obj) }