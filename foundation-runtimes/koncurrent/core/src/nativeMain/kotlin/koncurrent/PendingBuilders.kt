package koncurrent

actual inline fun <T> Executor.pending(noinline block: () -> T): Pending<T> = Later(this) { resolve, reject ->
    try {
        resolve(block())
    } catch (err: Throwable) {
        reject(err)
    }
}

actual inline fun <T> pending(noinline block: () -> T): Pending<T> = Later { resolve, reject ->
    try {
        resolve(block())
    } catch (err: Throwable) {
        reject(err)
    }
}

actual inline fun <T> ControlledPending(): Pending<T> = Later()