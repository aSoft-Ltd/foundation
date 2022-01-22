package kotlinx.serialization.mapper

inline fun wrappedMapOf(vararg pairs: Pair<String, Any?>) = WrappedMap(mapOf(*pairs))