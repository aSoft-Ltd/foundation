package later

import kotlin.js.Promise

fun <T, S> Later<Later<T>>.later(
    onFulfilled: ((T) -> S)
): Later<S> {
    return this.unsafeCast<Later<T>>().then(onFulfilled)
}

fun <T, S> Later<Later<T>>.later(
    onFulfilled: ((T) -> S)?,
    onRejected: ((Throwable) -> S)?
): Later<S> {
    return this.unsafeCast<Later<T>>().then(onFulfilled, onRejected)
}

fun <T> Later<T>.asPromise(): Promise<T> = asDynamic().promise ?: Promise<T> { resolve, reject ->
    then(onResolved = { resolve(it) }, onRejected = { reject(it) })
}.apply { asDynamic().promise = this }

fun <T> Promise<T>.asLater(): Later<T> = asDynamic().later ?: Later<T> { resolve, reject ->
    then(onFulfilled = { resolve(it) }, onRejected = { reject(it) })
}.apply { asDynamic().later = this }
