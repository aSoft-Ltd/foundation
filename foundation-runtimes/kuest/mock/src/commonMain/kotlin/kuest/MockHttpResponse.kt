package kuest

import koncurrent.Later
import koncurrent.Pending
import koncurrent.pending

inline fun <reified T> MockHttpResponse(body: T): HttpResponse = object : HttpResponse {
    override fun text(): Later<out String> = Later.resolve(
        when (body) {
            is String -> body
            else -> TODO()
        }
    )

    override fun toString(): String = "MockHttpResponse(body=$body)"
}