package kuest

import koncurrent.Pending
import koncurrent.ResolvedPending
import koncurrent.pending

actual open class HttpResponse {
    actual open fun text(): Pending<String> = ResolvedPending("")
}