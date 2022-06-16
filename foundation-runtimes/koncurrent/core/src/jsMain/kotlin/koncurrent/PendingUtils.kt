package koncurrent

actual fun <T, R> Pending<T>.then(executor: Executor, onResolved: ((T) -> R)) = then(onFulfilled = onResolved)

actual fun <T, R> Pending<T>.then(onResolved: ((T) -> R)) = then(onFulfilled = onResolved)

actual fun <T> Pending<T>.catch(onRejected: (Throwable) -> T): Pending<T> = catch(onRejected)

actual fun <T> Pending<T>.catch(executor: Executor, onRejected: (Throwable) -> T): Pending<T> = catch(onRejected)

actual fun <T> Pending<T>.complete(executor: Executor, finalizer: (Settled<T>) -> Unit): Pending<T> {
    then(
        onFulfilled = { finalizer(Fulfilled(it)) },
        onRejected = { finalizer(Rejected(it)) }
    )
    return this
}

actual fun <T> Pending<T>.complete(finalizer: (Settled<T>) -> Unit): Pending<T> {
    then(
        onFulfilled = { finalizer(Fulfilled(it)) },
        onRejected = { finalizer(Rejected(it)) }
    )
    return this
}

actual fun <T> Pending<T>.finally(executor: Executor, finalizer: () -> Unit): Pending<T> = finally(finalizer)

actual fun <T> Pending<T>.finally(finalizer: () -> Unit): Pending<T> = finally(finalizer)