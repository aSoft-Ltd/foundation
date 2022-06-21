import expect.expect
import koncurrent.MockExecutor
import koncurrent.SetTimeoutExecutor
import koncurrent.fewOf
import kotlin.test.Test

class FewApiTestJs {

    private val mockExecutor = MockExecutor()

    @Test
    fun can_get_a_valid_few_instance() {
        val f1 = fewOf(1, mockExecutor)
        expect(f1.executor).toBe(mockExecutor)

        val f2 = fewOf(2)
        expect(f2.executor).toBe(SetTimeoutExecutor)
    }

    @Test
    fun can_call_collect_on_it() {
        val few: dynamic = fewOf(0, mockExecutor)
        few.collect({ it ->
            console.log("Collecting $it")
            expect(it).toBe(0)
            console.log("Collected $it")
        })
    }

    @Test
    fun should_be_able_to_create_a_few() {
        fewOf(0).executeOn(mockExecutor).collect {
            println("Collecting $it")
            expect(it).toBe(0)
            println("Collected $it")
        }
    }

    @Test
    fun should_be_able_to_collect_multiple_few() {
        val name: dynamic = few({ it ->
            println("Emitting A")
            it.emit("A")
            println("Emitting N")
            it.emit("N")
            println("Emitting D")
            it.emit("D")
            println("Emitting Y")
            it.emit("Y")
            println("Finished emitting")
        }, mockExecutor)
        println("Haven't began collection, so this should run first")
        var collectedName = ""
        name.collect { it ->
            collectedName += it
            println("Collected $it")
        }
        println("Collected: $collectedName")
        expect(collectedName).toBe("ANDY")
    }

    @Test
    fun should_be_able_to_interpect_intermediate_values() {
        val name: dynamic = few({ it ->
            println("Emitting A")
            it.emit("A")
            println("Emitting N")
            it.emit("N")
            println("Emitting D")
            it.emit("D")
            println("Emitting Y")
            it.emit("Y")
            println("Finished emitting")
        }, mockExecutor)
        println("Haven't began collection, so this should run first")
        var collectedName = ""
        name.onEach({ it ->
            println("Interceptor 1: $it")
        }).onEach({ it ->
            println("Interceptor 2: $it")
        }).collect { it ->
            collectedName += it
            println("Collected $it")
        }
        println("Collected: $collectedName")
        expect(collectedName).toBe("ANDY")
    }
}