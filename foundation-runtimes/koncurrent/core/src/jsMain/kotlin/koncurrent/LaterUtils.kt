package koncurrent


fun <T, S> Later<Later<T>>.later(
    executor: Executor = this.executor,
    onFulfilled: ((T) -> S)
): Later<S> {
    return this.unsafeCast<Later<T>>().then(executor, onFulfilled)
}

fun <T, S> Later<Later<T>>.later(
    executor: Executor = this.executor,
    onFulfilled: ((T) -> S)?,
    onRejected: ((Throwable) -> S)?
): Later<S> {
    return this.unsafeCast<Later<T>>().then(executor, onFulfilled, onRejected)
}

fun <T> Later<T>.asPromise(): Promise<T> = asDynamic().promise ?: Promise<T> { resolve, reject ->
    then(executor, onResolved = { resolve(it) }, onRejected = { reject(it) })
}.apply { asDynamic().promise = this }

fun <T> Later<T>.asPromise(executor: Executor): Promise<T> = asDynamic().promise ?: Promise<T> { resolve, reject ->
    then(executor, onResolved = { resolve(it) }, onRejected = { reject(it) })
}.apply { asDynamic().promise = this }

fun <T> Promise<T>.asLater(executor: Executor): Later<T> = asDynamic().later ?: Later<T>(executor) { resolve, reject ->
    then(onFulfilled = { resolve(it) }, onRejected = { reject(it) })
}.apply { asDynamic().later = this }

fun <T> Promise<T>.asLater(): Later<T> = asDynamic().later ?: Later<T> { resolve, reject ->
    then(onFulfilled = { resolve(it) }, onRejected = { reject(it) })
}.apply { asDynamic().later = this }

external fun setTimeout(handler: dynamic, timeout: Int = definedExternally, vararg arguments: Any?): Int

internal actual fun <T> Later<T>.toNativeImplementation(): Pending<T> = asPromise()

internal actual fun <T> Later<T>.toNativeImplementation(executor: Executor): Pending<T> = asPromise()