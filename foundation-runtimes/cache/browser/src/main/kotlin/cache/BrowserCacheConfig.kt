package cache

import kotlinx.browser.localStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.w3c.dom.Storage

open class BrowserCacheConfig(
    override val namespace: String = DEFAULT_NAMESPACE,
    val storage: Storage = localStorage,
    val json: Json = Json {},
    override val scope: CoroutineScope = DEFAULT_SCOPE
) : CacheConfiguration(namespace, scope)