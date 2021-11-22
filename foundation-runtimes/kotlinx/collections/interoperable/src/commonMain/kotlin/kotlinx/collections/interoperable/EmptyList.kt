package kotlinx.collections.interoperable

import kotlin.collections.List as KList
import kotlin.collections.emptyList as kEmptyList

@PublishedApi
internal object EmptyList : List<Nothing>, KList<Nothing> by kEmptyList() {
    override fun toString(): String = "[]"
}