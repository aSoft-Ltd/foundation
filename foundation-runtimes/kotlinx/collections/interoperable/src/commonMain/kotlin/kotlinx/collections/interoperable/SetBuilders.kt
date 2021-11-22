//package kotlinx.collections.interoperable
//
//import kotlin.collections.List as KList
//
//inline fun <E> mutableSetOf(): MutableSet<E> = kotlin.collections.mutableSetOf<>()
//
//inline fun <E> mutableListOf(vararg elements: E): MutableList<E> = ArrayList(arrayListOf(*elements))
//
//fun <E> listOf(): List<E> = EmptyList
//
//fun emptyList(): List<Nothing> = EmptyList
//
//inline fun <E> listOf(vararg elements: E): List<E> = mutableListOf(*elements)
//
//inline fun <E> KList<E>.toInteroperableArrayList() = ArrayList(this)
//
//inline fun <E> KList<E>.toInteroperableMutableList(): MutableList<E> = toInteroperableArrayList()
//
//inline fun <E> KList<E>.toInteroperableList(): List<E> = toInteroperableMutableList()
//
//inline fun <E> Array<E>.toInteroperableList(): List<E> = toList().toInteroperableList()