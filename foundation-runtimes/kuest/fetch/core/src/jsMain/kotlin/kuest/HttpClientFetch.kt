package kuest

import kuest.internal.Fetcher

abstract class HttpClientFetch(val fetcher: Fetcher) : HttpClient {
    override fun get(url: String) = fetcher.fetch(url)
    override fun toString(): String = "HttpClientBrowserFetch"
}