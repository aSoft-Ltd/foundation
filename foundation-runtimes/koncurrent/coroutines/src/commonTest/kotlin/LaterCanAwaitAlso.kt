import koncurrent.*
import koncurrent.later.await
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LaterCanAwaitAlso {
    @Test
    fun should_be_able_to_recover_on_a_failure() = runTest {
        var zero = -1
        val result = Later<Int> { res, _ ->
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
        }.await()
        assertEquals(1, result)
    }
}