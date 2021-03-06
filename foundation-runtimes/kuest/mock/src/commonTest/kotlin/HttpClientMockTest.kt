import expect.expect
import kuest.*
import kotlin.test.Test

class HttpClientMockTest : AbstractHttpClientTest(client) {

    companion object {
        private val config = HttpClientMockConfig { req ->
            if (req.method == HttpMethod.Post) {
                """
                {
                  "id": 101
                }
                """.trimIndent()
            } else {
                """
                {
                  "userId": 1,
                  "id": 1,
                  "title": "delectus aut autem",
                  "completed": false
                }
                """.trimIndent()
            }
        }
        private val client = HttpClientMock(config)
    }

    @Test
    fun should_be_using_http_client_mock() {
        expect(client.toString()).toBe("HttpClientMock")
    }
}