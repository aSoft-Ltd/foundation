package kuest

import koncurrent.Pending
import koncurrent.pending

class HttpClientMock(val interceptor: (String) -> Any) : HttpClient {
    override fun get(url: String): Pending<HttpResponse> = pending {
        MockHttpResponse(interceptor(url))
    }

    override fun toString(): String = "HttpClientMock"
}