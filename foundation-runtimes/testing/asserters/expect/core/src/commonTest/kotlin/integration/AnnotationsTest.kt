package integration

import expect.AcceptanceTest
import expect.IntegrationTest
import expect.JsIgnore
import expect.expect
import kotlin.test.Test

class AnnotationsTest {

    @Test
    @JsIgnore
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }

    @IntegrationTest
    fun should_be_ignored_on_js() {
        expect(1 + 1).toBe(2)
    }

    @AcceptanceTest
    fun should_run_on_jvm_alone() {
        expect(1 + 1).toBe(2)
    }
}