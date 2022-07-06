package kuest

import koncurrent.Later
import org.w3c.fetch.RequestInit

abstract class HttpClientFetch : AbstractHttpClient() {
    abstract fun fetch(input: dynamic, init: RequestInit? = null): Later<out HttpResponse>

    abstract val mapper: FetchRequestInitBodyMapper
    override fun execute(req: HttpRequest<*>): Later<out HttpResponse> = fetch(
        input = req.url,
        init = RequestInit(
            method = req.method.value,
            headers = req.headers.toJson(),
            body = mapper.map(req.body)
        )
    )

    override fun toString(): String = "HttpClientFetch"
}