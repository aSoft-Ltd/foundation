import expect.expect
import koncurrent.Later
import koncurrent.later.await
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class CascadedValuesTest {
    @Test
    fun should_be_able_to_unwrap_cascaded_values() = runTest {
        val l = Later.resolve(Later.resolve(2))

        val res = l.then { it }

        expect<Any>(res.await()).toBe(2)
    }
}