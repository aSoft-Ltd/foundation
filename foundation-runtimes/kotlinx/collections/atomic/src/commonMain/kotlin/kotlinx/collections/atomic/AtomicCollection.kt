package kotlinx.collections.atomic

import kotlinx.atomicfu.AtomicRef
import kotlinx.collections.interoperable.Collection

interface AtomicCollection<E, C : Collection<E>> : Collection<E> {
    val atomic: AtomicRef<C>

    override val size: Int
        get() = atomic.value.size

    override fun contains(element: E): Boolean

    override fun isEmpty(): Boolean

    override fun iterator(): Iterator<E>
}