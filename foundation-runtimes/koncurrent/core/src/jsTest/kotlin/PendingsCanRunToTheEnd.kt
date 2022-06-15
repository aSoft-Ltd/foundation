import koncurrent.catch
import koncurrent.finally
import koncurrent.pending
import koncurrent.then
import koncurrent.Pending
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class PendingsCanRunToTheEnd {
    @Test
    fun should_run_to_completion() = pending<Int> {
        throw RuntimeException("Wooozaaaaa")
    }.catch {
        println("Recovering from ${it.message}")
        43
    }.then {
        println("Adding the pending object")
        it + 1
    }.then {
        println("trying to return unit")
    }.finally {
        println("completed")
    }
}