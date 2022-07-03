package cache

import expect.BasicExpectation

interface CacheExpectation<C : Cache> : BasicExpectation<C> {
    fun toHave(key: String)
}