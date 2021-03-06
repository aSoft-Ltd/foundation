package kotlinx.collections.interoperable

import kotlin.collections.MutableSet as KMutableSet

@PublishedApi
internal open class MutableSetWrapper<E>(private val set: KMutableSet<E>) : MutableSet<E>, KMutableSet<E> by set {
    override fun toString(): String = set.toString()

    override fun hashCode(): Int = set.hashCode()

    override fun equals(other: Any?): Boolean = set == other
}