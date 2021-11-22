@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package kotlinx.collections.interoperable

import kotlin.collections.Collection as KCollection

actual interface Collection<out E> : Iterable<E>, KCollection<E> {
    fun toArray(): Array<out E> {
        val array: Array<in Any?> = Array(size) { null }
        forEachWithIndex { e, index -> array[index] = e }
        return array as Array<out E>
    }
}