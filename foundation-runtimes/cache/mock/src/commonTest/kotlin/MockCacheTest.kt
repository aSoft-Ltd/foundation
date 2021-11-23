import cache.Cache
import cache.MockCache
import cache.exceptions.CacheLoadException
import expect.expect
import expect.expectFailure
import expect.toBe
import kotlinx.coroutines.runTest
import kotlinx.serialization.Serializable
import later.await
import kotlin.test.Test

class MockCacheTest {

    private val cache: Cache = MockCache()

    @Test
    fun should_be_able_to_load_and_save_primitively_easily() = runTest {
        cache.save("int", 1).await()
        expect(cache.load<Int>("int").await()).toBe(1)
    }

    @Serializable
    data class Person(val name: String)

    @Test
    fun should_be_able_to_load_and_save_custom_classes_easily() = runTest {
        cache.save("john", Person("John")).await()
        expect(cache.load<Person>("john").await()).toBe(Person("John"))
    }

    @Test
    fun should_throw_cache_load_exception() = runTest {
        val failure = expectFailure {
            cache.load<Int>("jane").await()
        }
        expect(failure).toBe<CacheLoadException>()
        failure.printStackTrace()
    }

    @Test
    fun should_throw_a_cache_load_exception_with_a_serialization_cause() = runTest {
        val failure = expectFailure {
            cache.load<Any>("jane").await()
        }
        expect(failure).toBe<CacheLoadException>()
        failure.printStackTrace()
    }
}