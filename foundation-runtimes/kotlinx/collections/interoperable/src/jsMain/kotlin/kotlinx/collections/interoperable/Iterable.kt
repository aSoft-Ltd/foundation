@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package kotlinx.collections.interoperable

import kotlin.collections.Iterable as KIterable

actual interface Iterable<out E> : KIterable<E> {
//    init {
//        asDynamic()[Symbol.iterator] = ::SymbolIterator
//    }

//    private fun SymbolIterator() = JsListIterator(iterator())

    fun forEach(lambda: (item: E) -> Unit) {
        for (item in this) lambda(item)
    }

    fun forEachWithIndex(lambda: (item: E, index: Int) -> Unit) {
        for (item in this) lambda(item, indexOf(item))
    }

    fun <O> map(transform: (item: E) -> O): List<O> {
        val l = mutableListOf<O>()
        for (item in this) l.add(transform(item))
        return l
    }

    fun <O> mapWithIndex(transform: (item: E, index: Int) -> O): List<O> {
        val l = mutableListOf<O>()
        for (item in this) l.add(transform(item, indexOf(item)))
        return l
    }
}