import expect.expect
import terminal.ConsoleTerminal
import kotlin.test.Test

class ConsoleTerminalTest {
    private val console = ConsoleTerminal()

    @Test
    fun should_print_em_tests_logs_on_the_console() {
        console.println("Testing output")
        expect(console.history).toContain("Testing output\n")
    }
}