package kuest

import koncurrent.Pending

interface HttpClient {
    fun execute(req: HttpRequest<*>): Pending<HttpResponse>

    fun get(url: String): Pending<HttpResponse>

    fun post(url: String, body: HttpRequestBody = EmptyRequestBody): Pending<HttpResponse>

    fun put(url: String, body: HttpRequestBody = EmptyRequestBody): Pending<HttpResponse>

    fun head(url: String): Pending<HttpResponse>

    fun delete(url: String, body: HttpRequestBody = EmptyRequestBody): Pending<HttpResponse>

    fun connect(url: String): Pending<HttpResponse>

    fun options(url: String): Pending<HttpResponse>
}