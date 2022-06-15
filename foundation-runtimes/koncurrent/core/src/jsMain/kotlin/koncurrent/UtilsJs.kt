package koncurrent

external fun setTimeout(handler: dynamic, timeout: Int = definedExternally, vararg arguments: Any?): Int

internal actual fun <T> Later<T>.toNativeImplementation(): Pending<T> = asPromise()

internal actual fun <T> Later<T>.toNativeImplementation(executor: Executor): Pending<T> = asPromise()