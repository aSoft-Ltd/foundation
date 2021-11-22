package kotlinx.collections.interoperable

import kotlin.collections.Collection

inline fun <E> Collection<E>.toInteroperableMutableList(): MutableList<E> = MutableListWrapper(toMutableList())

inline fun <E> Collection<E>.toInteroperableList(): List<E> = ListWrapper(toList())

inline fun <E> Collection<E>.toInteroperableMutableSet(): MutableSet<E> = MutableSetWrapper(toMutableSet())

inline fun <E> Collection<E>.toInteroperableSet(): Set<E> = SetWrapper(toSet())