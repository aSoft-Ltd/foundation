package functions

import kotlin.js.JsExport

@JsExport
fun interface Function<in T, out R> {
    fun invoke(obj: T): R
}