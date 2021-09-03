import kotlinx.serialization.json.Json
import platform.ExecutionEnvironment
import platform.Platform
import kotlin.test.Test

class PlatformTest {

    @Test
    fun should_print_the_name_of_the_platform() {
        println(Platform.name)
    }

    @Test
    fun should_print_the_serialized_values() {
        val platform = Platform
        val serializer = ExecutionEnvironment.serializer()
        val json = Json.encodeToString(serializer, platform)
        println(json)
    }
}