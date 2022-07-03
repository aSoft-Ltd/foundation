package cache

import koncurrent.Executor
import koncurrent.MockExecutor
import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmSynthetic

@Deprecated("In favour of CacheMockConfig")
interface MockCacheConfig : CacheConfig {
    val initialCache: MutableMap<String, Any?>

    companion object {

        @JvmField
        val DEFAULT_MAP = mutableMapOf<String, Any?>()

        @JvmField
        val DEFAULT_EXECUTOR = MockExecutor(name = "Mock Cache Executor")

        @JvmName("create")
        @JvmOverloads
        @JvmSynthetic
        operator fun invoke(
            namespace: String = CacheConfig.DEFAULT_NAMESPACE,
            initialCache: MutableMap<String, Any?> = DEFAULT_MAP,
            executor: Executor = DEFAULT_EXECUTOR
        ): MockCacheConfig = object : MockCacheConfig {
            override val namespace = namespace
            override val initialCache = initialCache
            override val executor = executor
        }
    }
}