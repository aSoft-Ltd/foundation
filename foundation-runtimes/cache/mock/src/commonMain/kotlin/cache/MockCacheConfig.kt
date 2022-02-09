package cache

import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface MockCacheConfig : CacheConfiguration {
    val initialCache: MutableMap<String, Any?>
    val simulationTime: Long

    companion object {
        @JvmField
        val DEFAULT_SIMULATION_TIME = 0L

        @JvmField
        val DEFAULT_MAP = mutableMapOf<String, Any?>()

        @JvmSynthetic
        operator fun invoke(
            namespace: String = CacheConfiguration.DEFAULT_NAMESPACE,
            initialCache: MutableMap<String, Any?> = DEFAULT_MAP,
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            scope: CoroutineScope = CacheConfiguration.DEFAULT_SCOPE
        ) = object : MockCacheConfig {
            override val namespace = namespace
            override val initialCache = initialCache
            override val simulationTime: Long = simulationTime
            override val scope: CoroutineScope = scope
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            namespace: String = CacheConfiguration.DEFAULT_NAMESPACE,
            initialCache: MutableMap<String, Any?> = DEFAULT_MAP,
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            scope: CoroutineScope = CacheConfiguration.DEFAULT_SCOPE
        ) = invoke(namespace, initialCache, simulationTime, scope)
    }
}