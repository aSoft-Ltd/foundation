import koncurrent.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CoroutinesCanAwait {
    @Test
    fun should_be_able_to_await_a_coroutine() = runTest {
        val result = pending {
            1
        }.await()
        assertEquals(1, result)
    }

    @Test
    fun should_be_able_to_recover_from_an_error_at_the_start_of_a_pipeline() = runTest {
        val result = pending<Int> {
            throw IllegalArgumentException("Whoops")
        }.catch {
            1
        }.then {
            it * 2
        }.then {
            it + 1
        }.await()
        assertEquals(3, result)
    }

    @Test
    fun should_be_able_to_recover_from_an_error_mid_pipeline_with_rejected_operations_in_between_pipeline() = runTest {
        val result = pending<Int> {
            throw IllegalArgumentException("Whoops")
        }.then {
            it + 1
        }.catch {
            1
        }.then {
            it * 2
        }.then {
            it + 1
        }.await()
        assertEquals(3, result)
    }

    @Test
    fun should_be_able_to_recover_from_an_error_mid_pipeline() = runTest {
        var zero = -1
        val result = pending<Int> {
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
        }.await()
        assertEquals(1, result)
    }
}