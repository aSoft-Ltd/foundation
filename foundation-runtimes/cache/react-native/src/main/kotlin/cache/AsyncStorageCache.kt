package cache

import cache.exceptions.CacheMissException
import kotlinx.coroutines.await
import kotlinx.serialization.KSerializer
import later.Later
import later.later

class AsyncStorageCache(
    override val config: AsyncStorageCacheConfig = AsyncStorageCacheConfig()
) : Cache(config) {

    private val storage = config.storage

    private val scope = config.scope

    private val json = config.json

    override fun <T> save(key: String, obj: T, serializer: KSerializer<T>): Later<T> = scope.later {
        storage.setItem(key, json.encodeToString(serializer, obj)).await()
        obj
    }

    override fun <T> load(key: String, serializer: KSerializer<T>): Later<T> = scope.later {
        val js = storage.getItem(key).await() ?: throw CacheMissException(key)
        json.decodeFromString(serializer, js)
    }

    override fun keys(): Later<Set<String>> = scope.later {
        storage.getAllKeys().await().toSet()
    }

    override fun size(): Later<Int> = scope.later {
        storage.getAllKeys().await().size
    }
}