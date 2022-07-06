package kuest.internal

import koncurrent.Promise
import org.khronos.webgl.ArrayBuffer
import org.w3c.fetch.Headers
import org.w3c.fetch.ResponseInit
import org.w3c.fetch.ResponseType
import org.w3c.files.Blob
import org.w3c.xhr.FormData

open external class Response(body: dynamic = definedExternally, init: ResponseInit = definedExternally) : Body {
    open val type: ResponseType
    open val url: String
    open val redirected: Boolean
    open val status: Short
    open val ok: Boolean
    open val statusText: String
    open val headers: Headers
    open val body: dynamic
    open val trailer: Promise<Headers>
    override val bodyUsed: Boolean
    fun clone(): Response
    override fun arrayBuffer(): Promise<ArrayBuffer>
    override fun blob(): Promise<Blob>
    override fun formData(): Promise<FormData>
    override fun json(): Promise<Any?>
    override fun text(): Promise<String>

    companion object {
        fun error(): Response
        fun redirect(url: String, status: Short = definedExternally): Response
    }
}