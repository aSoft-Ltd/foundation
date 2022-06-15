package koncurrent

expect fun <T, R> Pending<T>.then(onResolved: ((T) -> R), onRejected: ((Throwable) -> R)? = null): Pending<R>

expect fun <T, R> Pending<T>.then(executor: Executor, onResolved: ((T) -> R), onRejected: ((Throwable) -> R)? = null): Pending<R>