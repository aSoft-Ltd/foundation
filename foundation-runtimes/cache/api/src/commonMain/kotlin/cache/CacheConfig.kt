package cache

import kotlin.jvm.JvmStatic

interface CacheConfig {
    val namespace: String

    companion object {
        @JvmStatic
        val DEFAULT_NAMESPACE = "app"
    }
}