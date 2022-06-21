import expect.expect
import koncurrent.MockExecutor
import koncurrent.few
import koncurrent.few.executeOn
import koncurrent.fewOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class FewApiTest {

    private val mockExecutor = MockExecutor()

    @Test
    fun should_be_able_to_create_a_few() = runTest {
        fewOf(0).executeOn(mockExecutor).collect {
            println("Collecting $it")
            expect(it).toBe(0)
            println("Collected $it")
        }
    }

    @Test
    fun should_be_able_to_collect_multiple_few() = runTest {
        val name = few {
            println("Emitting A")
            emit("A")
            println("Emitting N")
            emit("N")
            println("Emitting D")
            emit("D")
            println("Emitting Y")
            emit("Y")
            println("Finished emitting")
        }.executeOn(mockExecutor)
        println("Haven't began collection, so this should run first")
        var collectedName = ""
        name.collect {
            println("Collecting $it")
            collectedName += it
        }
        println("Collected: $collectedName")
    }
}