import koncurrent.pending
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