package kotlinx.collections.interoperable

import kotlin.collections.Set as KSet

@PublishedApi
internal open class SetWrapper<out E>(private val set: KSet<E>) : Set<E>, KSet<E> by set {
    override fun toString(): String = set.toString()

    override fun hashCode(): Int = set.hashCode()

    override fun equals(other: Any?): Boolean = set == other
}