import expect.expect
import koncurrent.Later
import koncurrent.MockExecutor
import koncurrent.later.then
import koncurrent.later.unwrap
import kotlin.test.Test

class NestedValuesTest {

    @Test
    fun should_be_able_to_unwrap_cascaded_values() {
        val executor = MockExecutor()
        Later.resolve(Later.resolve(2), executor).unwrap().then {
            println("Comparing")
            expect(it).toBe(2)
            println("Finished comparing")
        }
        println("Done here")
    }

    @Test
    fun should_be_able_to_switch_executors() {
        val e1 = MockExecutor("Mock Executor 1")

        val e2 = MockExecutor("Mock Executor 2")

        val e3 = MockExecutor("Mock Executor 3")
        Later.resolve(0, e1).then(e2) {
            expect(it).toBe(0)
            it + 1
        }.then(e3) {
            expect(it).toBe(1)
            it * 2
        }.then(e1) {
            expect(it).toBe(2)
            1 / (it - 2)
        }.catch(e2) {
            0
        }.finally {
            println("Finished processing later")
        }
    }

    @Test
    fun should_keep_execution_on_first_executor() {
        Later.resolve(0, MockExecutor("Mock Executor 1")).then {
            expect(it).toBe(0)
            it + 1
        }.then {
            expect(it).toBe(1)
            it * 2
        }.then {
            expect(it).toBe(2)
            1 / (it - 2)
        }.catch {
            0
        }.finally {
            println("Finished processing later")
        }
    }
}