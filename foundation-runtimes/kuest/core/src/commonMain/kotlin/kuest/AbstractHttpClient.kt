package kuest

import koncurrent.Pending

abstract class AbstractHttpClient : HttpClient {

    override fun get(url: String) = execute(HttpRequest(url))

    override fun post(url: String, body: HttpRequestBody) = execute(HttpRequest(url = url, method = HttpMethod.Post, body = body))

    override fun put(url: String, body: HttpRequestBody) = execute(HttpRequest(url = url, method = HttpMethod.Put, body = body))

    override fun head(url: String): Pending<HttpResponse> = execute(HttpRequest(url = url, method = HttpMethod.Head))

    override fun delete(url: String, body: HttpRequestBody) = execute(HttpRequest(url = url, method = HttpMethod.Delete, body = body))

    override fun connect(url: String) = execute(HttpRequest(url = url, method = HttpMethod.Connect))

    override fun options(url: String) = execute(HttpRequest(url = url, method = HttpMethod.Options))
}