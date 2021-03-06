import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import later.later
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
