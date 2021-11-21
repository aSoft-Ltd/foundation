package terminal

class ConsoleTerminal(
    private val config: TerminalConfig = TerminalConfig()
) : Terminal, TerminalOutput by ConsoleTerminalOutput() {
    override val history: TerminalHistory get() = config.history
    override fun executeReadLine(prompt: String): String {
        executePrint(prompt)
        return kotlin.io.readln()
    }
}