package cache

import cache.CacheConfig.Companion.DEFAULT_NAMESPACE
import cache.CacheConfig.Companion.DEFAULT_SCOPE
import cache.npm.AsyncStorage
import cache.npm.ReactNativeAsyncStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json

interface AsyncStorageCacheConfig : CacheConfig {
    val storage: ReactNativeAsyncStorage
    val json: Json

    companion object {
        val DEFAULT_JSON = Json { }

        val DEFAULT_STORAGE: ReactNativeAsyncStorage = AsyncStorage

        operator fun invoke(
            namespace: String = DEFAULT_NAMESPACE,
            storage: ReactNativeAsyncStorage = AsyncStorage,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = object : AsyncStorageCacheConfig {
            override val namespace = namespace
            override val storage = storage
            override val json = json
            override val scope = scope
        }
    }
}