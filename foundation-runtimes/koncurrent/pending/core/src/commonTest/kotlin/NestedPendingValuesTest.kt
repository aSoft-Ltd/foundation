import expect.expect
import koncurrent.ResolvedPending
import koncurrent.pending
import koncurrent.pending.await
import koncurrent.pending.awaitChain
import koncurrent.pending.unwrap
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class NestedPendingValuesTest {
    @Test
    fun should_be_able_to_unwrap_nested_values_with_the_unwrap_method() = runTest {
        val p = pending { pending { 2 } }

        val r = p.unwrap { it + 1 }

        expect<Any>(r.await()).toBe(3)
    }

    @Test
    fun should_be_able_to_await_nested_values() = runTest {
        val p = ResolvedPending(ResolvedPending(2))

        val r = p.awaitChain()

        expect(r).toBe(2)
    }
}