import expect.expect
import koncurrent.Later
import koncurrent.MockExecutor
import koncurrent.later.catch
import koncurrent.later.then
import kotlin.test.Test

class ErrorHandlingTestCase {

    val executor = MockExecutor()

    @Test
    fun should_execute_catch_when_it_encounters_an_error() {
        var blockcount = 0
        var result = 0
        Later.resolve(0, executor).then {
            blockcount++
            it divide it
        }.catch {
            blockcount++
            5
        }.then {
            blockcount++
            result = it
        }
        expect(blockcount).toBe(3)
        expect(result).toBe(5)
    }

    @Test
    fun should_not_execute_catch_if_there_are_no_errors() {
        var blockcount = 0
        var result = 0
        Later.resolve(4, executor).then {
            blockcount++
            it + 7
        }.catch {
            blockcount++
            5
        }.then {
            blockcount++
            result = it
        }
        expect(blockcount).toBe(2)
        expect(result).toBe(11)
    }

    @Test
    fun can_totally_chain_errors_and_then_blocks_continuously() {
        var blockcount = 0
        var result = 0
        Later.resolve(4, executor).then {
            blockcount++
            it + 7
        }.then {
            blockcount++
            it - 11
        }.then {
            blockcount++
            it divide it
        }.catch {
            blockcount++
            5
        }.then {
            blockcount++
            result = it
        }
        expect(blockcount).toBe(5, "Chained code blocks where not executed as expected")
        expect(result).toBe(5)
    }

    @Test
    fun can_totally_chain_many_errors_and_then_blocks_continuously() {
        var blockcount = 0
        var result = 0
        Later.resolve(4, executor).then {
            blockcount++
            it + 7
        }.then {
            blockcount++
            it - 11
        }.then {
            blockcount++
            it divide it
        }.catch {
            blockcount++
            5
        }.then {
            blockcount++
            it - 5
        }.then {
            blockcount++
            it divide it
        }.catch {
            blockcount++
            0
        }.then {
            blockcount++
            result = it
        }
        expect(blockcount).toBe(8, "Chained code blocks where not executed as expected")
        expect(result).toBe(0)
    }
}