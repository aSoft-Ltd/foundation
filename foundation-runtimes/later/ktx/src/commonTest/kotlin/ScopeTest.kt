import expect.expect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.runTest
import later.await
import later.later
import kotlin.test.Test

class ScopeTest {
    val defaultScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val unconfinedScope = CoroutineScope(SupervisorJob() + Dispatchers.Unconfined)

    @Test
    fun should_not_throw_invalid_mutability_exception_on_default_scope() = runTest {
        val later = defaultScope.later {
            delay(10)
            3
        }
        val result = later.await()
        expect(result).toBe(3)
    }

    @Test
    fun should_not_throw_an_invalid_mutability_exception_on_an_unconfined_scope() = runTest {
        val later = defaultScope.later {
            delay(10)
            4
        }
        val result = later.await()
        expect(result).toBe(4)
    }
}