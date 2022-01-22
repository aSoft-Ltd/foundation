package kotlinx.serialization.mapper

import kotlinx.serialization.Serializable
import kotlin.reflect.KProperty

@Serializable(with = WrappedMapSerializer::class)
class WrappedMap(private val map: Map<String, Any?>) : Map<String, Any?> by map {
    inline operator fun <reified T> getValue(thisRef: Any?, property: KProperty<*>) = when (T::class) {
        String::class -> this[property.name].toString()
        Int::class -> this[property.name].toString().toInt()
        Long::class -> this[property.name].toString().toLong()
        Double::class -> this[property.name].toString().toLong()
        Boolean::class -> this[property.name].toString().toBoolean()
        else -> this[property.name]
    } as T

    override fun toString(): String = map.entries.joinToString(
        separator = ",",
        prefix = "[",
        postfix = "]"
    ) { "${it.key}: ${it.value}" }
}