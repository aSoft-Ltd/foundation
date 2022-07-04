package kuest

import expect.expect
import koncurrent.later.flatten
import koncurrent.later.test
import koncurrent.later.then
import kotlin.test.Test

abstract class AbstractHttpClientTest(open val client: HttpClient) {

    @Test
    fun client_should_be_able_to_perform_post_requests() = client.post(
        url = "https://jsonplaceholder.typicode.com/posts",
        body = JsonRequestBody("""{"name":"John"}""")
    ).flatten {
        it.text()
    }.then {
        expect(it).toBe(
            """
            {
              "id": 101
            }
        """.trimIndent()
        )
    }.test()

    @Test
    fun client_get_should_not_fail() = client.get(
        url = "https://jsonplaceholder.typicode.com/todos/1"
    ).flatten {
        it.text()
    }.then {
        expect(it).toBe(
            """
            {
              "userId": 1,
              "id": 1,
              "title": "delectus aut autem",
              "completed": false
            }
        """.trimIndent()
        )
    }.test()

    fun should_use_a_better_syntax_for_getting_response_body() = client.get(
        url = "https://jsonplaceholder.typicode.com/todos/1"
    ).flatten {
        it.text()
    }.then {
        expect(it).toBe(
            """
            {
              "userId": 1,
              "id": 1,
              "title": "delectus aut autem",
              "completed": false
            }
        """.trimIndent()
        )
    }.test()
}