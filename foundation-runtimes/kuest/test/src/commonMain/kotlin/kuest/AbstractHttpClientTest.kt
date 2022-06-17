package kuest

import expect.expect
import koncurrent.coroutines.await
import koncurrent.flatMap
import koncurrent.then
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

abstract class AbstractHttpClientTest(open val client: HttpClient) {
    @Test
    fun should_easily_send_a_get_request() = runTest {
        val res = client.get("https://jsonplaceholder.typicode.com/todos/1").then {
            it.text()
        }.flatMap { it }
        expect(res.await()).toBe("")
    }
}