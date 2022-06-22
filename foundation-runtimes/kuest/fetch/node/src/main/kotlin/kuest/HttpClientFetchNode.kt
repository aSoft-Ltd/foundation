package kuest

import koncurrent.Promise
import kuest.npm.nodeFetch
import org.w3c.fetch.RequestInit

class HttpClientFetchNode : HttpClientFetch() {

    override val mapper = FetchRequestInitBodyMapper()

    override fun fetch(input: dynamic, init: RequestInit?): Promise<HttpResponse> {
        return if (init != null && init != undefined) {
            nodeFetch(input, init)
        } else nodeFetch(input)
    }

    override fun toString(): String = "HttpClientFetchNode"
}