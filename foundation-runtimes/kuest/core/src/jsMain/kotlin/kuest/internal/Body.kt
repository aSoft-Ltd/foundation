package kuest.internal

import koncurrent.Pending
import org.khronos.webgl.ArrayBuffer
import org.w3c.files.Blob
import org.w3c.xhr.FormData

public external interface Body {
    val bodyUsed: Boolean
    fun arrayBuffer(): Pending<ArrayBuffer>
    fun blob(): Pending<Blob>
    fun formData(): Pending<FormData>
    fun json(): Pending<Any?>
    fun text(): Pending<String>
}