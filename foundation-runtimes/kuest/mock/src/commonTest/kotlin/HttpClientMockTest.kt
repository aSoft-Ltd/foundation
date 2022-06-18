import expect.expect
import kuest.AbstractHttpClientTest
import kuest.HttpClientMock
import kuest.MockHttpResponse
import kotlin.test.Test

class HttpClientMockTest : AbstractHttpClientTest(client) {

    companion object {
        private val client = HttpClientMock { _ ->
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

    @Test
    fun should_be_using_http_client_browser_test() {
        expect(client.toString()).toBe("HttpClientMock")
    }
}