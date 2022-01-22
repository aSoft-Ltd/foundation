import cache.MockCache
import cache.exceptions.CacheLoadException
import expect.expect
import expect.expectFailure
import expect.toBe
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import later.await
import kotlin.test.Test

class MockCacheTest {

    private val cache = MockCache()

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

    @Test
    fun should_return_Unit_when_an_existing_item_in_the_cache_was_removed() = runTest {
        val saved = cache.save("test", 1).await()
        expect(saved).toBe(1)

        val removed = cache.remove("test").await()
        expect(removed).toBe(Unit)
    }

    @Test
    fun should_return_null_when_removing_a_non_existent_key() = runTest {
        val removed = cache.remove("pip").await()
        expect(removed).toBe(null)
    }

    @Test
    fun should_clear_the_whole_cache() = runTest {
        cache.save("one", 1).await()
        cache.save("two", 2).await()
        cache.save("three", 3).await()

        cache.clear()
        expect(cache.loadOrNull<Int?>("two").await()).toBe(null)
        expect(cache.keys().await()).toBeEmpty()
    }
}