package kotlinx.collections.interoperable

import kotlin.collections.MutableList as KMutableList

@PublishedApi
internal class MutableListWrapper<E>(
    private val list: KMutableList<E>
) : ListWrapper<E>(list), MutableList<E>, KMutableList<E> by list {
    override fun addAll(elements: Collection<E>): Boolean = list.addAll(elements)

    override fun removeAll(elements: Collection<E>): Boolean = list.removeAll(elements)

    override fun retainAll(elements: Collection<E>): Boolean = list.retainAll(elements)

    override fun addAll(index: Int, elements: Collection<E>): Boolean = list.addAll(index, elements)

    override fun containsAll(elements: Collection<E>): Boolean = list.containsAll(elements)

    override fun subList(
        fromIndex: Int,
        toIndex: Int
    ): MutableList<E> = list.subList(fromIndex, toIndex).toInteroperableMutableList()
}