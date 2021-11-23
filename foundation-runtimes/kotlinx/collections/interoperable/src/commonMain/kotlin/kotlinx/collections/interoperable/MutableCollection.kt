@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package kotlinx.collections.interoperable

import kotlin.js.JsExport
import kotlin.collections.MutableCollection as KMutableCollection

interface MutableCollection<E> : KMutableCollection<E>, Collection<E> {
    fun addAll(elements: Collection<E>): Boolean
    fun removeAll(elements: Collection<E>): Boolean
    fun retainAll(elements: Collection<E>): Boolean
}