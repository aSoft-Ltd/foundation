package terminal

import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmSynthetic

interface TerminalConfig {
    val history: TerminalHistory

    companion object {
        @JvmField
        val DEFAULT_TERMINAL_HISTORY = TerminalHistory()

        @JvmSynthetic
        operator fun invoke(
            history: TerminalHistory = DEFAULT_TERMINAL_HISTORY
        ) = object : TerminalConfig {
            override val history = history
        }

        @JvmSynthetic
        @JvmOverloads
        fun create(
            history: TerminalHistory = DEFAULT_TERMINAL_HISTORY
        ) = invoke(history)
    }
}