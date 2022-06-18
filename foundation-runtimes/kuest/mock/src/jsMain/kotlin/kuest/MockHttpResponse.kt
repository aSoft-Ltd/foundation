package kuest

actual inline fun <reified T> MockHttpResponse(body: T): HttpResponse {
    if (body is String) {
        return HttpResponse(body)
    } else {
        TODO()
    }
}