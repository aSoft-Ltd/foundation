import koncurrent.*
import koncurrent.pending.catch
import koncurrent.pending.then
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class CanConstructAFunctioningPending {
    @Test
    fun should_resolve_quickly() {
        val p = pending { 42 }
        p.then {
            println("Got $it")
        }
    }

    @Test
    fun should_be_able_to_recover_on_a_failure() = runTest {
        val p: Pending<Int> = pending { throw RuntimeException("Wooozaaaaa") }
        val increamented = p.catch {
            println("Recovering from ${it.message}")
            43
        }.then {
            println("Adding the pending object")
            it + 1
        }
        println("Increamented value is $increamented")
    }
}