@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.SetSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.collections.Set as KSet

@Serializable(with = SetSerializer::class)
interface Set<out E> : Collection<E>, KSet<E>