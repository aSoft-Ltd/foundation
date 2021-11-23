@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.MutableListSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.collections.MutableList as KMutableList

@Serializable(with = MutableListSerializer::class)
interface MutableList<E> : List<E>, KMutableList<E>, MutableCollection<E> {
    @JsName("addAllAtIndex")
    fun addAll(index: Int, elements: Collection<E>): Boolean
    fun containsAll(elements: Collection<E>): Boolean
}