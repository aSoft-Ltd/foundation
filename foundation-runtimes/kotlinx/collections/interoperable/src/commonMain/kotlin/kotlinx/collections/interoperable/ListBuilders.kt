package kotlinx.collections.interoperable

import kotlin.collections.mutableListOf as kMutableListOf

inline fun <E> mutableListOf(): MutableList<E> = MutableListWrapper(kMutableListOf())

inline fun <E> mutableListOf(vararg elements: E): MutableList<E> = MutableListWrapper(kMutableListOf(*elements))

inline fun <E> listOf(): List<E> = EmptyList

inline fun emptyList(): List<Nothing> = EmptyList

inline fun <E> listOf(vararg elements: E): List<E> = mutableListOf(*elements)


inline fun <E> interoperableListOf(): List<E> = EmptyList
inline fun <E> interoperableListOf(vararg elements: E): List<E> = MutableListWrapper(kMutableListOf(*elements))

inline fun <E> interoperableMutableListOf(): MutableList<E> = MutableListWrapper(kMutableListOf())
inline fun <E> interoperableMutableListOf(vararg elements: E): List<E> = MutableListWrapper(kMutableListOf(*elements))