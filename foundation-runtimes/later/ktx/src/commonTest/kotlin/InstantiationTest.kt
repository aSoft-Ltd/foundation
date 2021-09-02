import kotlinx.coroutines.delay
import kotlinx.coroutines.runTest
import later.later
import later.then
import kotlin.test.Test

class InstantiationTest {
    @Test
    fun should_instantiate_from_coroutine_scope_and_give_result() = runTest {
        later {
            println("Running later now")
            delay(2000)
            50
        }.then {
            println("Got: $it")
            println("Computing final result")
            it * 2
        }.finally {
            println("Completed with state $it")
        }
    }
}
