import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import later.Later
import later.loadToNextEventLoop
import kotlin.test.Test

class LaterTestCommon {
    fun later(value: Int) = Later<Int> { resolve, reject ->
        loadToNextEventLoop {
            if (value < 5) reject(Exception("Number($value) is less than 5"))
            else resolve(value)
        }
    }

    fun Later<Int>.process() = error {
        println("Error: ${it.message}")
        5
    }.then {
        println("Got $it")
    }.then { }

    @Test
    fun should_return_basic_values() = runTest {
        val later1 = later(6)

        val then1 = later1.process()

        val later2 = later(4)

        later2.process()

        delay(1000)
    }

    @Test
    fun finally_test() = runTest {
        later(4).error {
            println("Caught error: $it")
            5
        }.complete {
            println("I am certain that this is settled now")
            println(it)
        }
        delay(10)
    }
}