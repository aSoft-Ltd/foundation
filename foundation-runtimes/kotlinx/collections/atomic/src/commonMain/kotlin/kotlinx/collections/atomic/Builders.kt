package kotlinx.collections.atomic

fun <E> mutableAtomicListOf(vararg elements: E): MutableList<E> {
    return MutableAtomicList(elements.toMutableList())
}

fun <K, V> mutableAtomicMapOf(vararg pairs: Pair<K, V>): MutableMap<K, V> {
    return MutableAtomicMap(pairs.toMap().toMutableMap())
}