package kotlinx.collections.interoperable.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Encoder

abstract class CollectionSerializer<E, C : Collection<E>>(val elementSerializer: KSerializer<E>) : KSerializer<C> {
    override val descriptor: SerialDescriptor = elementSerializer.descriptor
    override fun serialize(encoder: Encoder, value: C) {
        encoder.encodeSerializableValue(ListSerializer(elementSerializer), value.toList())
    }
}