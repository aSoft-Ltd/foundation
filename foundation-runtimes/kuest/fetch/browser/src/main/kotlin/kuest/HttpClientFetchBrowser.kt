package kuest

import koncurrent.later.asLater
import koncurrent.later.then
import kuest.internal.HttpResponseImpl
import kuest.internal.window
import org.w3c.fetch.RequestInit

class HttpClientFetchBrowser : HttpClientFetch() {

    override val mapper by lazy { BrowserFetchRequestInitBodyMapper() }
    override fun fetch(input: dynamic, init: RequestInit?) = if (init != null && init != undefined) {
        window.fetch(input, init)
    } else {
        window.fetch(input)
    }.asLater().then { HttpResponseImpl(it) }

    override fun toString(): String = "HttpClientFetchBrowser"
}