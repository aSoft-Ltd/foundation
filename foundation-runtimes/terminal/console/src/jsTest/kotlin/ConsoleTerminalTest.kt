import terminal.npm.promptsync.prompt
import kotlin.test.Test

class ConsoleTerminalTest {
    @Test
    fun should_check() {
        val p = platform.utils.common.require<dynamic>("prompt-sync")
        console.log(p)
    }

    @Test
    fun should_call_create() {
        val name = prompt("What is your name? ")
        console.log("Hello $name")
    }
}