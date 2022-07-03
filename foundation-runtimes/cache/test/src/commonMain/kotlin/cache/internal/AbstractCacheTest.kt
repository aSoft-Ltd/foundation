package cache.internal

import cache.Cache
import cache.exceptions.CacheLoadException
import cache.load
import cache.loadOrNull
import cache.save
import expect.BasicExpectation
import expect.expect
import expect.toBe
import koncurrent.later.catch
import koncurrent.later.flatten
import koncurrent.later.test
import koncurrent.later.then
import kotlinx.serialization.Serializable
import kotlin.test.Test

abstract class AbstractCacheTest(val cache: Cache) {

    inline fun <T> loudExpect(message: String, e: T): BasicExpectation<T> {
        println(message)
        val res = expect(e)
        println("Finished $message")
        return res
    }

    @Test
    fun should_be_able_to_load_and_save_primitively_easily() = cache.save("int", 1).flatten {
        cache.load<Int>("int")
    }.then {
        loudExpect("Loading", it).toBe(1)
    }.test()

    @Serializable
    data class Person(val name: String)

    @Test
    fun should_be_able_to_load_and_save_custom_classes_easily() = cache.save("john", Person("John")).flatten {
        cache.load<Person>("john")
    }.then {
        loudExpect("Loading", it).toBe(Person("John"))
    }.test()

    @Test
    fun should_throw_cache_load_exception() = cache.load<Int>("jane").catch {
        val exp = expect(it).toBe<CacheLoadException>()
        expect(exp.message).toBe("Failed to load object with key=jane from the cache")
        0
    }.then {
        expect(it).toBe(0)
    }.test()

    @Test
    fun should_throw_a_cache_load_exception_with_a_serialization_cause() = cache.load<Any>("jane").catch {
        val exp = expect(it).toBe<CacheLoadException>()
        expect(exp.key).toBe("jane")
    }.test()

    @Test
    fun should_return_Unit_when_an_existing_item_in_the_cache_was_removed() = cache.save("test", 1).flatten {
        cache.remove("test")
    }.then {
        expect(it).toBe(Unit)
    }.test()

    @Test
    fun should_return_null_when_removing_a_non_existent_key() = cache.remove("pip").then {
        expect(it).toBe(null)
    }.test()


    @Test
    fun should_clear_the_whole_cache() = cache.save("one", 1).flatten {
        cache.save("two", 2)
    }.flatten {
        cache.save("three", 3)
    }.flatten {
        cache.clear()
    }.flatten {
        cache.loadOrNull<Int>("two")
    }.flatten {
        expect(it).toBe(null)
        cache.keys()
    }.then { keys ->
        expect(collection = keys).toBeEmpty()
    }.test()
}