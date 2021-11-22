@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.MutableListSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.collections.MutableList as KMutableList

@Serializable(with = MutableListSerializer::class)
interface MutableList<E> : List<E>, KMutableList<E>