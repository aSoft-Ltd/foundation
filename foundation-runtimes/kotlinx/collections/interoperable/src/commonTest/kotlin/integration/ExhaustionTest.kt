package integration

import expect.expect
import kotlin.test.Test

class ExhaustionTest {
    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}