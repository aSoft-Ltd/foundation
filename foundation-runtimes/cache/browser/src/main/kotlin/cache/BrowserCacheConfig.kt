package cache

import koncurrent.Executor
import kotlinx.browser.localStorage
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import org.w3c.dom.Storage

interface BrowserCacheConfig : CacheConfig {
    val storage: Storage
    val codec: StringFormat

    companion object {
        val DEFAULT_STORAGE: Storage = localStorage
        val DEFAULT_JSON = Json { encodeDefaults = true }

        operator fun invoke(
            namespace: String = CacheConfig.DEFAULT_NAMESPACE,
            storage: Storage = DEFAULT_STORAGE,
            executor: Executor = CacheConfig.DEFAULT_EXECUTOR,
            codec: StringFormat = DEFAULT_JSON,
        ) = object : BrowserCacheConfig {
            override val storage: Storage = storage
            override val codec: StringFormat = codec
            override val executor: Executor = executor
            override val namespace: String = namespace
        }
    }
}