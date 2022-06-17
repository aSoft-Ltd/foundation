package kuest

import koncurrent.Pending

interface HttpClient {
    fun get(url: String): Pending<HttpResponse>
}