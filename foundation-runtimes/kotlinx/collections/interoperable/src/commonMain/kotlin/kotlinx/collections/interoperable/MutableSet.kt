@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.MutableSetSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.collections.MutableSet as KMutableSet

@Serializable(with = MutableSetSerializer::class)
interface MutableSet<E> : Set<E>, KMutableSet<E>