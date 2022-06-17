package kuest.internal

import koncurrent.Promise
import kuest.HttpResponse
import org.w3c.fetch.RequestInit

external interface Fetcher {
    fun fetch(input: dynamic, init: RequestInit = definedExternally): Promise<HttpResponse>
}