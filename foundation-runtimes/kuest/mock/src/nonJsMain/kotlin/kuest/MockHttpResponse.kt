package kuest

import koncurrent.Pending
import koncurrent.pending

actual inline fun <reified T> MockHttpResponse(body: T): HttpResponse = object : HttpResponse() {
    override fun text(): Pending<String> = pending {
        when (body) {
            is String -> body
            else -> TODO()
        }
    }
}