import koncurrent.*
import koncurrent.pending.await
import koncurrent.pending.catch
import koncurrent.pending.then
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CoroutinesCanAwait {
    val executor = MockExecutor()

    @Test
    fun should_be_able_to_await_a_coroutine(): TestResult {
        val result = executor.pending {
            1
        }
        return runTest {
            assertEquals(1, result.await())
        }
    }

    @Test
    fun should_be_able_to_recover_from_an_error_at_the_start_of_a_pipeline(): TestResult {
        val result = executor.pending<Int> {
            throw IllegalArgumentException("Whoops")
        }.catch {
            1
        }.then {
            it * 2
        }.then {
            it + 1
        }
        return runTest {
            assertEquals(3, result.await())
        }
    }

    @Test
    fun should_be_able_to_recover_from_an_error_mid_pipeline_with_rejected_operations_in_between_pipeline(): TestResult {
        val result = executor.pending<Int> {
            throw IllegalArgumentException("Whoops")
        }.then {
            it + 1
        }.catch {
            1
        }.then {
            it * 2
        }.then {
            it + 1
        }

        return runTest {
            assertEquals(3, result.await())
        }
    }

    @Test
    fun should_be_able_to_recover_from_an_error_mid_pipeline(): TestResult {
        var zero = -1
        val result = executor.pending {
            zero = 0
            25
        }.then {
            1 / zero // so that it fails
        }.then {
            it * 2
        }.catch {
            zero
        }.then {
            it + 1
        }
        return runTest {
            assertEquals(1, result.await())
        }
    }
}