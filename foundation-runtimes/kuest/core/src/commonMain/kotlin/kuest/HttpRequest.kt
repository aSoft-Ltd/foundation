package kuest

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic

interface HttpRequest<out B : HttpRequestBody> {
    val url: String
    val headers: Headers
    val method: HttpMethod
    val body: B

    companion object {
        @JvmStatic
        @JvmName("create")
        inline operator fun invoke(
            url: String,
            headers: Headers = headersOf(),
            method: HttpMethod = HttpMethod.Get,
            body: HttpRequestBody = EmptyRequestBody
        ): HttpRequest<*> = HttpRequestImpl(url, headers, method, body)
    }
}