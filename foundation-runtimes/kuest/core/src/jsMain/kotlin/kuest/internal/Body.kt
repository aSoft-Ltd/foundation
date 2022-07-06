package kuest.internal

import koncurrent.Promise
import org.khronos.webgl.ArrayBuffer
import org.w3c.files.Blob
import org.w3c.xhr.FormData

public external interface Body {
    val bodyUsed: Boolean
    fun arrayBuffer(): Promise<ArrayBuffer>
    fun blob(): Promise<Blob>
    fun formData(): Promise<FormData>
    fun json(): Promise<Any?>
    fun text(): Promise<String>
}