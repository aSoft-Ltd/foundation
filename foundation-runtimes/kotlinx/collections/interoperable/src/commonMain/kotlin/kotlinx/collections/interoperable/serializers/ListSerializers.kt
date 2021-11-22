package kotlinx.collections.interoperable.serializers

import kotlinx.collections.interoperable.*
import kotlinx.collections.interoperable.ListWrapper
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encoding.Decoder

internal class ListSerializer<E>(elementSerializer: KSerializer<E>) : CollectionSerializer<E, List<E>>(elementSerializer) {
    override fun deserialize(decoder: Decoder): List<E> = ListWrapper(decoder.decodeSerializableValue(ListSerializer(elementSerializer)))
}

internal class MutableListSerializer<E>(elementSerializer: KSerializer<E>) : CollectionSerializer<E, MutableList<E>>(elementSerializer) {
    override fun deserialize(decoder: Decoder): MutableList<E> = decoder.decodeSerializableValue(ListSerializer(elementSerializer)).toInteroperableMutableList()
}