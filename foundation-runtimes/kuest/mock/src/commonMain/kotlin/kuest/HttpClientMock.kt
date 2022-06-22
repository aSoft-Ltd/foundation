package kuest

import koncurrent.pending

class HttpClientMock(private val config: HttpClientMockConfig) : AbstractHttpClient() {

    override fun execute(req: HttpRequest<*>) = config.executor.pending {
        MockHttpResponse(config.resolve(req))
    }

    override fun toString(): String = "HttpClientMock"
}