package kuest

import koncurrent.Later

class HttpClientMock(private val config: HttpClientMockConfig) : AbstractHttpClient() {

    override fun execute(req: HttpRequest<*>) = Later.resolve(MockHttpResponse(config.resolve(req)), config.executor)

    override fun toString(): String = "HttpClientMock"
}