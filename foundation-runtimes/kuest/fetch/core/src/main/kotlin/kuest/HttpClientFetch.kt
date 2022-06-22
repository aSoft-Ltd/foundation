package kuest

import koncurrent.Pending
import koncurrent.Promise
import org.w3c.fetch.RequestInit

abstract class HttpClientFetch : AbstractHttpClient() {
    abstract fun fetch(input: dynamic, init: RequestInit? = null): Promise<HttpResponse>

    abstract val mapper: FetchRequestInitBodyMapper
    override fun execute(req: HttpRequest<*>): Pending<HttpResponse> = fetch(
        input = req.url,
        init = RequestInit(
            method = req.method.value,
            headers = req.headers.toJson(),
            body = mapper.map(req.body)
        )
    )

    override fun toString(): String = "HttpClientBrowserFetch"
}