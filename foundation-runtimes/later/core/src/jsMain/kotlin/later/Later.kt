@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package later

import kotlin.js.Promise

actual open class Later<out T> actual constructor(
    executor: ((resolve: (T) -> Unit, reject: ((Throwable) -> Unit)) -> Unit)?
) : BaseLater<T>(executor) {
    actual companion object {
        actual fun <T> resolve(value: T): Later<T> {
            val l = Later<T>()
            l.resolveWith(value)
            return l
        }

        actual fun reject(error: Throwable): Later<Nothing> {
            val l = Later<Nothing>()
            l.rejectWith(error)
            return l
        }
    }

    fun asPromise(): Promise<T> = asDynamic().promise ?: Promise<T> { resolve, reject ->
        then(onResolved = { resolve(it) }, onRejected = { reject(it) })
    }.apply { asDynamic().promise = this }
}