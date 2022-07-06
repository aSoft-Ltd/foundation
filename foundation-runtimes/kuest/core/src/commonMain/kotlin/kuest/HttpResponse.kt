package kuest

import koncurrent.Later
import koncurrent.Pending

interface HttpResponse {
    fun text(): Later<out String>
}