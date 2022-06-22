package functions

import kotlin.js.JsExport

@JsExport
fun interface Supplier<out T> {
    fun get(): T
}