package cache

import kotlinx.collections.atomic.mutableAtomicMapOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

open class MockCacheConfig(
    override val namespace: String = DEFAULT_NAMESPACE,
    val initialCache: MutableMap<String, Any?> = mutableAtomicMapOf(),
    val simulationTime: Long = 0L,
    override val scope: CoroutineScope = DEFAULT_SCOPE
) : CacheConfiguration(namespace, scope)