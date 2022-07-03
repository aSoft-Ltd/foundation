import cache.internal.AbstractCacheTest
import expect.expect
import cache.CacheMock

import kotlin.test.Test

class CacheMockTest : AbstractCacheTest(CacheMock()) {

    @Test
    fun should_be_using_a_mock_cache() {
        expect(cache.toString()).toBe("CacheMock(namespace=app)")
    }
}