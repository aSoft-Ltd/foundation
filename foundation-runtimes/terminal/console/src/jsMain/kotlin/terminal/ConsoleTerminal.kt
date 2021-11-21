package terminal

import kotlinx.browser.window
import platform.Platform

class ConsoleTerminal(
    private val config: TerminalConfig = TerminalConfig()
) : Terminal, TerminalOutput by ConsoleTerminalOutput() {
    override val history: TerminalHistory get() = config.history
    override fun executeReadLine(prompt: String): String {
        return if (Platform.isBrowser) {
            window.prompt(prompt) ?: throw Exception("Failed to retrieve a prompt value")
        } else {
            window.prompt(prompt) ?: throw Exception("Failed to retrieve a prompt value")
        }
    }
}