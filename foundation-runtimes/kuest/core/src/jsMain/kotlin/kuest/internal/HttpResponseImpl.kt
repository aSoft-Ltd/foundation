package kuest.internal

import koncurrent.Later
import koncurrent.later.asLater
import kuest.HttpResponse

class HttpResponseImpl(val nativeResponse: Response) : HttpResponse {
    override fun text(): Later<out String> = nativeResponse.text().asLater()
}