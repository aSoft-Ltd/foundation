@file:JsExport

package terminal

import kotlin.js.JsExport

open class TerminalHistory(val config: TerminalHistoryConfig = TerminalHistoryConfig()) {
    fun push(any: Any?) {
        config.list.add(0, any.toString())
        if (config.list.size > config.size) {
            config.list.removeLast()
        }
    }

    override fun equals(other: Any?) = when (other) {
        is TerminalHistory -> config.list == other.config.list
        is Iterable<*> -> config.list == other.toList()
        else -> false
    }

    override fun toString(): String = config.list.toString()

    override fun hashCode(): Int = config.list.hashCode()
}