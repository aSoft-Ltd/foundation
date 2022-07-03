package cache

import cache.npm.AsyncStorage
import cache.npm.ReactNativeAsyncStorage
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json

interface AsyncStorageCacheConfig : CacheConfig {
    val storage: ReactNativeAsyncStorage
    val codec: StringFormat

    companion object {
        val DEFAULT_CODEC = Json { }

        val DEFAULT_STORAGE: ReactNativeAsyncStorage = AsyncStorage

        operator fun invoke(
            namespace: String = CacheConfig.DEFAULT_NAMESPACE,
            storage: ReactNativeAsyncStorage = AsyncStorage,
            codec: StringFormat = DEFAULT_CODEC,
            executor: Executor = CacheConfig.DEFAULT_EXECUTOR
        ) = object : AsyncStorageCacheConfig {
            override val namespace = namespace
            override val storage = storage
            override val codec = codec
            override val executor: Executor = executor
        }
    }
}