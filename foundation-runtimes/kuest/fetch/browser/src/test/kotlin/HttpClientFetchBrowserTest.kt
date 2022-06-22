import expect.expect
import kuest.AbstractHttpClientTest
import kuest.HttpClientFetchBrowser
import kotlin.test.Test

class HttpClientFetchBrowserTest : AbstractHttpClientTest(HttpClientFetchBrowser()) {
    @Test
    fun should_be_using_http_client_browser_test() {
        expect(client.toString()).toBe("HttpClientFetchBrowser")
    }
}