package cache

import kotlinx.browser.localStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.w3c.dom.Storage

open class BrowserCacheConfig(
    val namespace: String = "app",
    val storage: Storage = localStorage,
    val json: Json = Json {},
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : CacheConfiguration(scope)