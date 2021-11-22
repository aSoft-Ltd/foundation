@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.collections.List as KList

@Serializable(with = ListSerializer::class)
interface List<out E> : Collection<E>, KList<E>