package kuest

import koncurrent.Pending
import koncurrent.pending

class HttpClientMock(val config: HttpClientMockConfig) : HttpClient {
    override fun get(url: String): Pending<HttpResponse> = config.executor.pending {
        MockHttpResponse(config.interceptor(url))
    }

    override fun toString(): String = "HttpClientMock"
}