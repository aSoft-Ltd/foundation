package kuest

import koncurrent.Pending
import koncurrent.pending

actual open class HttpResponse {
    actual fun text(): Pending<String> = pending { "" }
}