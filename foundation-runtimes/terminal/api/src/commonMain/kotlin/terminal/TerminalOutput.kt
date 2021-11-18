@file:JsExport

package terminal

import kotlin.js.JsExport

interface TerminalOutput {

    val history: TerminalHistory

    fun <T> executePrint(any: T)

    fun <T> print(any: T) {
        history.push(any)
        executePrint(any)
    }

    fun <T> println(any: T) = print("$any\n")
}