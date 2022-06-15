package koncurrent

internal actual fun <T> Later<T>.toNativeImplementation(): Pending<T> = this

internal actual fun <T> Later<T>.toNativeImplementation(executor: Executor): Pending<T> {
    val later = Later<T>(executor)
    then(
        executor = executor,
        onResolved = { later.resolveWith(it) },
        onRejected = { later.rejectWith(it) }
    )
    return later
}