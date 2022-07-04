package kuest

import koncurrent.later.asLater
import koncurrent.later.then
import kuest.internal.HttpResponseImpl
import kuest.npm.nodeFetch
import org.w3c.fetch.RequestInit

class HttpClientFetchNode : HttpClientFetch() {

    override val mapper = FetchRequestInitBodyMapper()

    override fun fetch(input: dynamic, init: RequestInit?) = if (init != null && init != undefined) {
        nodeFetch(input, init)
    } else {
        nodeFetch(input)
    }.asLater().then { HttpResponseImpl(it) }

    override fun toString(): String = "HttpClientFetchNode"
}