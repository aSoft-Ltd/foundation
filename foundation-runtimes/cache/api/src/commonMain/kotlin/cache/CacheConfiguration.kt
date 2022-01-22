package cache

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmStatic

open class CacheConfiguration(
    open val namespace: String,
    open val scope: CoroutineScope
) {
    companion object {
        @JvmStatic
        val DEFAULT_NAMESPACE = "app"
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())
    }
}