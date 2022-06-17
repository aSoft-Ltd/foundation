package kuest

import koncurrent.Promise
import org.w3c.fetch.RequestInit

abstract class HttpClientFetch : HttpClient {
    abstract fun fetch(input: dynamic, init: RequestInit? = null): Promise<HttpResponse>
    override fun get(url: String) = fetch(url)
    override fun toString(): String = "HttpClientBrowserFetch"
}