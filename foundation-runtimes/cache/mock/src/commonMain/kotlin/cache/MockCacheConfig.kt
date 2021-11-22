package cache

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

open class MockCacheConfig(
    val initialCache: MutableMap<String, Any?> = mutableMapOf(),
    val simulationTime: Long = 0L,
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : CacheConfiguration(scope)