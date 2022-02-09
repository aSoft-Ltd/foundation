package cache

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmStatic

interface CacheConfiguration {
    val namespace: String
    val scope: CoroutineScope

    companion object {
        @JvmStatic
        val DEFAULT_NAMESPACE = "app"

        @JvmStatic
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())
    }
}