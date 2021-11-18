package terminal

class ConsoleTerminalOutput(config: TerminalHistoryConfig = TerminalHistoryConfig()) : TerminalOutput {
    override val history = TerminalHistory(config)
    override fun <T> executePrint(any: T) = kotlin.io.print(any)
}