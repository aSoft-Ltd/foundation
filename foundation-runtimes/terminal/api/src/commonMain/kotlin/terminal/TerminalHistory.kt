@file:JsExport

package terminal

import kotlin.js.JsExport

open class TerminalHistory(private val config: TerminalHistoryConfig = TerminalHistoryConfig()) : List<String> by config.list {
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