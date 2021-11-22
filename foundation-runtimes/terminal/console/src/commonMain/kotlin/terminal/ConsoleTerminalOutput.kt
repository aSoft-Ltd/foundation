package terminal

class ConsoleTerminalOutput(
    config: TerminalHistoryConfig = TerminalHistoryConfig()
) : TerminalOutput {
    override val history = TerminalHistory(config)
    override fun executePrint(any: Any?) = kotlin.io.println(any)
}