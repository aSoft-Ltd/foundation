package kotlinx.collections.interoperable

import kotlin.collections.MutableList as KMutableList

@PublishedApi
internal class MutableListWrapper<E>(
    private val list: KMutableList<E>
) : ListWrapper<E>(list), MutableList<E>, KMutableList<E> by list {
    
    override fun subList(
        fromIndex: Int,
        toIndex: Int
    ): MutableList<E> = list.subList(fromIndex, toIndex).toInteroperableMutableList()
}