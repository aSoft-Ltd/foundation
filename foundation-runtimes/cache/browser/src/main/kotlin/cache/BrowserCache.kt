package cache

import cache.exceptions.CacheMissException
import kotlinx.serialization.KSerializer
import later.Later
import later.later

class BrowserCache(
    override val config: BrowserCacheConfig = BrowserCacheConfig()
) : Cache(config) {
    private val storage = config.storage

    private val scope = config.scope

    private val json = config.json

    private val namespace = config.namespace

    override fun size() = scope.later { storage.length }

    @OptIn(ExperimentalStdlibApi::class)
    override fun keys() = scope.later {
        buildSet {
            for (i in 0 until storage.length) add(storage.key(i) as String)
        }
    }

    override fun <T> save(key: String, obj: T, serializer: KSerializer<T>) = scope.later {
        storage.setItem("${namespace}:${key}", json.encodeToString(serializer, obj))
        obj
    }

    override fun <T> load(key: String, serializer: KSerializer<T>): Later<T> = scope.later {
        val js = storage.getItem("${namespace}:${key}") ?: throw CacheMissException(key)
        json.decodeFromString(serializer, js)
    }
}