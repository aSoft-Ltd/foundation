package kotlinx.collections.interoperable

inline fun <E> Array<E>.toInteroperableList(): List<E> = ListWrapper(toList())