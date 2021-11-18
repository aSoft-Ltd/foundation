@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package terminal

import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface TerminalHistoryConfig {
    val size: Int
    val list: MutableList<String>

    companion object {
        @JvmField
        val DEFAULT_MAX_HISTORY_SIZE = 100

        @JvmField
        val DEFAULT_INITIAL_HISTORY = mutableListOf<String>()

        @JvmSynthetic
        operator fun invoke(
            size: Int = DEFAULT_MAX_HISTORY_SIZE,
            initial: MutableList<String> = DEFAULT_INITIAL_HISTORY
        ): TerminalHistoryConfig = object : TerminalHistoryConfig {
            override val size: Int = size
            override val list = initial
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            size: Int = DEFAULT_MAX_HISTORY_SIZE,
            initial: MutableList<String> = DEFAULT_INITIAL_HISTORY
        ) = invoke(size, initial)
    }
}