import kotlinx.serialization.Serializable
import kotlinx.serialization.mapper.WrappedMap
import kotlinx.serialization.mapper.wrappedMapOf

@Serializable
data class Person(val props: WrappedMap = wrappedMapOf())