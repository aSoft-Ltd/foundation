package kuest

@PublishedApi
internal class HttpRequestImpl(
    override val url: String,
    override val headers: Headers,
    override val method: HttpMethod,
    override val body: HttpRequestBody
) : HttpRequest<HttpRequestBody>