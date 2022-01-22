package cache

import cache.npm.AsyncStorage
import cache.npm.AsyncStorageStatic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json

class AsyncStorageCacheConfig(
    override val namespace: String = DEFAULT_NAMESPACE,
    val storage: AsyncStorageStatic = AsyncStorage,
    val json: Json = Json { },
    override val scope: CoroutineScope = DEFAULT_SCOPE
) : CacheConfiguration(namespace, scope)