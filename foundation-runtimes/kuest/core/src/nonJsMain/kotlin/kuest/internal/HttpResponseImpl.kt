package kuest.internal

import koncurrent.Later
import kuest.HttpResponse

class HttpResponseImpl : HttpResponse {
    override fun text(): Later<out String> = Later.resolve("")
}