import expect.expect
import kuest.AbstractHttpClientTest
import kuest.HttpClientFetchNode
import kotlin.test.Test

class HttpClientFetchNodeTest : AbstractHttpClientTest(HttpClientFetchNode()) {
    @Test
    fun should_be_using_http_client_browser_test() {
        expect(client.toString()).toBe("HttpClientFetchBrowser")
    }
}