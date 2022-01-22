import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.WrappedMap
import kotlinx.serialization.mapper.wrappedMapOf
import kotlin.test.Test

class SerializerTest {
    @Test
    fun should_serialize_an_empty_wrapped_map() {
        val map = wrappedMapOf(
            "string" to "Hello",
            "float" to 1.5,
            "integer" to 29,
            "list" to listOf("Hello", 1.5, 1),
            "map" to mapOf(
                "string" to "Hello",
                "float" to 1.5,
                "integer" to 29,
                "list" to listOf("Hello", 1.5, 1),
            )
        )
        println(Json.encodeToString(map))
    }

    @Test
    fun implied_serialization() {
        println(Json.encodeToString(Person(wrappedMapOf("test" to 1))))
    }

    @Test
    fun should_deserialize_into_a_wrapped_map_easily() {
        val json = """{"string":"Hello","float":1.5,"integer":29,"list":["Hello",1.5,1],"map":{"string":"Hello","float":1.5,"integer":29,"list":["Hello",1.5,1]}}"""
        println(Json.decodeFromString<WrappedMap>(json))
    }
}