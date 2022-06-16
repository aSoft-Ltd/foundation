import expect.expect
import koncurrent.collect
import koncurrent.map
import koncurrent.pending
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class PendingCanSafelyBeExecuteInSuspendContexts {
    @Test
    fun should_map_from_one_pending_type_to_another() = runTest {
        pending {
            10
        }.map {
            delay(10)
            it * 2
        }.map {
            it - 2
        }.collect {
            expect(it).toBe(18)
        }
    }
}