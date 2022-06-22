package kuest

import koncurrent.Promise
import kuest.internal.window
import org.w3c.fetch.RequestInit

class HttpClientFetchBrowser : HttpClientFetch() {

    override val mapper by lazy { BrowserFetchRequestInitBodyMapper() }
    override fun fetch(input: dynamic, init: RequestInit?): Promise<HttpResponse> {
        return if (init != null && init != undefined) window.fetch(input, init) else window.fetch(input)
    }

    override fun toString(): String = "HttpClientFetchBrowser"
}