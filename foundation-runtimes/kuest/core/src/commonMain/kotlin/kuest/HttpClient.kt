package kuest

import koncurrent.Later

interface HttpClient {
    fun execute(req: HttpRequest<*>): Later<out HttpResponse>

    fun get(url: String): Later<out HttpResponse>

    fun post(url: String, body: HttpRequestBody = EmptyRequestBody): Later<out HttpResponse>

    fun put(url: String, body: HttpRequestBody = EmptyRequestBody): Later<out HttpResponse>

    fun head(url: String): Later<out HttpResponse>

    fun delete(url: String, body: HttpRequestBody = EmptyRequestBody): Later<out HttpResponse>

    fun connect(url: String): Later<out HttpResponse>

    fun options(url: String): Later<out HttpResponse>
}