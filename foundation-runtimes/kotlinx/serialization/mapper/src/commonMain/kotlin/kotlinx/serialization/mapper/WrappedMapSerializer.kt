package kotlinx.serialization.mapper

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializer(forClass = WrappedMap::class)
object WrappedMapSerializer : KSerializer<WrappedMap> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("WrappedMap")
    override fun deserialize(decoder: Decoder): WrappedMap {
        return when (val jsonElement = decoder.decodeSerializableValue(JsonElement.serializer())) {
            JsonNull -> wrappedMapOf()
            is JsonObject -> WrappedMap(jsonElement.toKMap())
            is JsonArray -> error("You are trying to deserialize a json array into a map. Are you nuts?")
            else -> error("Can't serialize the coming json into a map")
        }
    }

    override fun serialize(encoder: Encoder, value: WrappedMap) {
        encoder.encodeSerializableValue(JsonElement.serializer(), value.toJsonElement())
    }
}