package kuest

import koncurrent.Pending

expect open class HttpResponse {
    fun text(): Pending<String>
}