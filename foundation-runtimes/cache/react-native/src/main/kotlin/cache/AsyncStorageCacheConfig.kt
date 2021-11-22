package cache

import cache.npm.AsyncStorage
import cache.npm.AsyncStorageStatic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json

class AsyncStorageCacheConfig(
    val storage: AsyncStorageStatic = AsyncStorage,
    val json: Json = Json { },
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : CacheConfiguration(scope)