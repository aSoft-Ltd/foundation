package cache

import kotlinx.coroutines.CoroutineScope

open class CacheConfiguration(
    open val scope: CoroutineScope
)