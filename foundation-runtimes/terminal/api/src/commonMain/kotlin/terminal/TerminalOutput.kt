@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package terminal

import kotlin.js.JsExport

interface TerminalOutput {

    val history: TerminalHistory

    fun executePrint(any: Any?)

    fun print(any: Any?) {
        history.push(any)
        executePrint(any)
    }

    fun println(any: Any?) = print("$any\n")
}