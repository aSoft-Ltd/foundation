package functions

import kotlin.js.JsExport

@JsExport
actual fun interface Runnable {
    actual fun run()
}