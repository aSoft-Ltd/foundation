package koncurrent

actual fun <T> Executor.pending(block: () -> T): Pending<T> = Promise { resolve, reject ->
    try {
        resolve(block())
    } catch (err: Throwable) {
        reject(err)
    }
}

actual fun <T> pending(block: () -> T): Pending<T> = Promise { resolve, reject ->
    try {
        resolve(block())
    } catch (err: Throwable) {
        reject(err)
    }
}