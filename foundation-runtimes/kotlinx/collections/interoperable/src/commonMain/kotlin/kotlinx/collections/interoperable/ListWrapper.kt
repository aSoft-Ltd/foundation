package kotlinx.collections.interoperable

import kotlin.collections.List as KList

@PublishedApi
internal open class ListWrapper<out E>(private val list: KList<E>) : List<E>, KList<E> by list {
    override fun subList(
        fromIndex: Int,
        toIndex: Int
    ): List<E> = list.subList(fromIndex, toIndex).toInteroperableList()

    override fun toString(): String = list.toString()

    override fun hashCode(): Int = list.hashCode()

    override fun equals(other: Any?): Boolean = list == other
}