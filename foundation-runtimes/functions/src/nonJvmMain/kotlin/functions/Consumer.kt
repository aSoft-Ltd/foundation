package functions

import kotlin.js.JsExport

@JsExport
actual fun interface Consumer<T> {
    actual fun accept(obj: T)
}