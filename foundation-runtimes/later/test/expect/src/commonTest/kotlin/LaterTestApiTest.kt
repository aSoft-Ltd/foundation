import expect.expect
import later.Later
import later.toBeFulfilled
import later.toBeFulfilledWith
import later.toBeSettled
import kotlin.test.Test

class LaterTestApiTest {
    @Test
    fun should_convert_to_deferred_and_return_results() {
        val later = Later.resolve(50)
        expect(later).toBeSettled()
        expect(later).toBeFulfilled()
        expect(later) {
            toBeSettled()
            toBeFulfilled()
            toBeFulfilledWith(50)
        }
    }
}