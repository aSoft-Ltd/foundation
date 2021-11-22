package cache

import cache.exceptions.CacheMissException
import kotlinx.coroutines.delay
import kotlinx.serialization.KSerializer
import later.Later
import later.later

class MockCache(
    override val config: MockCacheConfig = MockCacheConfig()
) : Cache(config) {
    private val cache = config.initialCache

    private val scop get() = config.scope

    override fun keys(): Later<Set<String>> = scop.later {
        delay(config.simulationTime)
        cache.keys
    }

    override fun size(): Later<Int> = scop.later {
        delay(config.simulationTime)
        cache.size
    }

    override fun <T> save(key: String, obj: T, serializer: KSerializer<T>) = scop.later {
        delay(config.simulationTime)
        cache[key] = obj
        obj
    }

    override fun <T> load(key: String, serializer: KSerializer<T>): Later<T> = scop.later {
        delay(config.simulationTime)
        val obj = cache[key] ?: throw CacheMissException(key)
        obj as T
    }
}