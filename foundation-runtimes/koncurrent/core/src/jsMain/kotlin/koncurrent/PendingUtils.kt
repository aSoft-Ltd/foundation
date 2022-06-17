package koncurrent

actual inline fun <T, R> Pending<T>.then(executor: Executor, noinline onResolved: ((T) -> R)) = then(onFulfilled = onResolved)

actual inline fun <T, R> Pending<T>.then(noinline onResolved: ((T) -> R)) = then(onFulfilled = onResolved)

actual inline fun <T> Pending<T>.catch(noinline onRejected: (Throwable) -> T): Pending<T> = catch(onRejected)

actual inline fun <T> Pending<T>.catch(executor: Executor, noinline onRejected: (Throwable) -> T): Pending<T> = catch(onRejected)

actual inline fun <T> Pending<T>.complete(executor: Executor, noinline finalizer: (Settled<T>) -> Unit): Pending<T> {
    then(
        onFulfilled = { finalizer(Fulfilled(it)) },
        onRejected = { finalizer(Rejected(it)) }
    )
    return this
}

actual inline fun <T> Pending<T>.complete(noinline finalizer: (Settled<T>) -> Unit): Pending<T> {
    then(
        onFulfilled = { finalizer(Fulfilled(it)) },
        onRejected = { finalizer(Rejected(it)) }
    )
    return this
}

actual inline fun <T> Pending<T>.finally(executor: Executor, noinline finalizer: () -> Unit): Pending<T> = finally(finalizer)

actual inline fun <T> Pending<T>.finally(noinline finalizer: () -> Unit): Pending<T> = finally(finalizer)

actual inline fun <T> Pending<T>.resolveWith(value: T): Boolean {
    val s = state ?: return false
    if (s is PendingState) {
        val r = resolveFn ?: return false
        r(value)
        state = Fulfilled(value)
        return true
    }
    return false
}

actual inline fun <T> Pending<T>.rejectWith(exception: Throwable): Boolean {
    val s = state ?: return false
    if (s is PendingState) {
        val r = rejectFn ?: return false
        r(exception)
        state = Rejected(exception)
        return true
    }
    return false
}

actual inline fun <T, R> Pending<Pending<T>>.flatMap(noinline onFulfilled: (T) -> R): Pending<R> = unsafeCast<Pending<T>>().then(onFulfilled)