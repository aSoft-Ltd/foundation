package koncurrent

actual fun <T> pending(executor: Executor, block: () -> T): Pending<T> = Later(executor) { resolve, reject ->
    try {
        resolve(block())
    } catch (err: Throwable) {
        reject(err)
    }
}

actual fun <T> pending(block: () -> T): Pending<T> = Later { resolve, reject ->
    try {
        resolve(block())
    } catch (err: Throwable) {
        reject(err)
    }
}