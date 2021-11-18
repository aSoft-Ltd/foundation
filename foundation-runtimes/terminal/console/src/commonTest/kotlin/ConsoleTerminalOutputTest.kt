import expect.expect
import terminal.ConsoleTerminalOutput
import terminal.TerminalHistoryConfig
import terminal.TerminalOutput
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ConsoleTerminalOutputTest {
    val config = TerminalHistoryConfig()
    val output: TerminalOutput = ConsoleTerminalOutput(config)

    @Test
    fun should_print_out_any_thing() {
        output.print("People")
        output.println("Stars")
        output.println("Stars")

        assertTrue { output.history == listOf("Stars\n", "Stars\n", "People") }
    }
}