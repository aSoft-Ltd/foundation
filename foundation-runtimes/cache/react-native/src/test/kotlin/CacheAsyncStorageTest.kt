import cache.CacheAsyncStorage
import cache.internal.AbstractCacheTest
import expect.expect
import cache.CacheMock

import kotlin.test.Test

class CacheAsyncStorageTest : AbstractCacheTest(CacheAsyncStorage()) {

    companion object {

    }

    @Test
    fun should_be_using_a_mock_cache() {
        expect(cache.toString()).toBe("CacheAsyncStorage(namespace=app)")
    }
}