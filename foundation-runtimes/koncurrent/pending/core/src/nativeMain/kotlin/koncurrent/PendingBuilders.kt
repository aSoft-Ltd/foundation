@file:Suppress("FunctionName")

package koncurrent

@PublishedApi
internal inline fun <T> laterBuilder(resolve: (T) -> Unit, reject: (Throwable) -> Unit, block: () -> T) {
    try {
        resolve(block())
    } catch (err: Throwable) {
        reject(err)
    }
}

actual inline fun <T> Executor.pending(noinline block: () -> T): Pending<T> = Later(this) { resolve, reject ->
    laterBuilder(resolve, reject, block)
} as Later<T>

actual inline fun <T> pending(noinline block: () -> T): Pending<T> = Later { resolve, reject ->
    laterBuilder(resolve, reject, block)
} as Later<T>

actual inline fun <T> ResolvedPending(value: T): Pending<T> = Later.resolve(value) as Pending<T>

actual inline fun <T> RejectedPending(error: Throwable): Pending<T> = Later.reject(error) as Pending<T>

actual inline fun <T> ControlledPending(): Pending<T> = Later()