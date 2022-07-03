package cache

import cache.exceptions.CacheMissException
import kotlinx.serialization.KSerializer
import koncurrent.Later
import koncurrent.later

class MockCache(
    val config: MockCacheConfig = MockCacheConfig()
) : Cache {
    private val cache = config.initialCache

    private val executor get() = config.executor

    private val namespace get() = config.namespace

    override fun keys(): Later<out Set<String>> = executor.later { cache.keys }

    override fun size(): Later<out Int> = executor.later { cache.size }

    override fun <T> save(key: String, obj: T, serializer: KSerializer<T>) = executor.later {
        cache["$namespace:$key"] = obj
        obj
    }

    override fun <T> load(key: String, serializer: KSerializer<T>) = executor.later {
        val obj = cache["$namespace:$key"] ?: throw CacheMissException(key)
        obj as T
    }

    override fun remove(key: String) = executor.later {
        val removed = cache.remove("$namespace:$key")
        if (removed != null) Unit else null
    }

    override fun clear() = executor.later { cache.clear() }
}