import koncurrent.*
import koncurrent.later.await
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LaterCanAwait {
    val executor = MockExecutor()

    @Test
    fun should_be_able_to_recover_on_a_failure(): TestResult {
        var zero = -1
        val result = Later<Int>(executor) { res, _ ->
            zero = 0
            res(25)
        }.then {
            1 / zero // so that it fails
        }.then {
            it * 2
        }.catch {
            zero
        }.then {
            it + 1
        }

        flow { emit(0) }.onEach { }
        return runTest {
            assertEquals(1, result.await())
        }
    }
}