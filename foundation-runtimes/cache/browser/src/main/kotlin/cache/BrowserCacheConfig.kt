package cache

import kotlinx.browser.localStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.w3c.dom.Storage

interface BrowserCacheConfig : CacheConfiguration {
    val storage: Storage
    val json: Json

    companion object {
        val DEFAULT_STORAGE: Storage = localStorage
        val DEFAULT_JSON = Json { encodeDefaults = true }

        operator fun invoke(
            namespace: String = CacheConfiguration.DEFAULT_NAMESPACE,
            storage: Storage = DEFAULT_STORAGE,
            json: Json = DEFAULT_JSON,
            scope: CoroutineScope = CacheConfiguration.DEFAULT_SCOPE,
        ) = object : BrowserCacheConfig {
            override val storage: Storage = storage
            override val json: Json = json
            override val namespace: String = namespace
            override val scope: CoroutineScope = scope
        }
    }
}