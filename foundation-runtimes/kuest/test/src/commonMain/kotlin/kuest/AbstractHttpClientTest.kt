package kuest

import expect.expect
import expect.toBe
import koncurrent.pending.await
import koncurrent.pending.then
import koncurrent.pending.unwrap
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

abstract class AbstractHttpClientTest(open val client: HttpClient) {

    @Test
    fun client_get_should_not_fail() = runTest {
        val res = client.get("https://jsonplaceholder.typicode.com/todos/1").then {
            it.text()
        }.unwrap()

        expect<Any>(res.await()).toBe(
            """
            {
              "userId": 1,
              "id": 1,
              "title": "delectus aut autem",
              "completed": false
            }
        """.trimIndent()
        )
    }

    @Test
    fun should_use_a_better_syntax_for_getting_response_body() = runTest {
        val res = client.get("https://jsonplaceholder.typicode.com/todos/1").bodyAsText()
        expect(res.await()).toBe(
            """
            {
              "userId": 1,
              "id": 1,
              "title": "delectus aut autem",
              "completed": false
            }
        """.trimIndent()
        )
    }

    @Test
    fun should_easily_send_a_get_request() = runTest {
        val res = client.get("https://jsonplaceholder.typicode.com/todos/1")
        val body = res.then {
            it.text()
        }.unwrap()

        expect(body.await()).toBe(
            """
            {
              "userId": 1,
              "id": 1,
              "title": "delectus aut autem",
              "completed": false
            }
        """.trimIndent()
        )
    }

    @Test
    fun redundant_check_for_native_tests() {
        expect(1 + 1).toBe(2)
    }

    @Test
    fun should_easily_send_a_get_request_with_await() = runTest {
        val res = client.get("https://jsonplaceholder.typicode.com/todos/1")
        val body = res.then {
            it.text()
        }.await()
        expect(body.await()).toBe(
            """
            {
              "userId": 1,
              "id": 1,
              "title": "delectus aut autem",
              "completed": false
            }
        """.trimIndent()
        )
    }
}