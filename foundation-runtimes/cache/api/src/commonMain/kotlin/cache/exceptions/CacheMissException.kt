package cache.exceptions

open class CacheMissException(
    override val key: String,
    override val cause: Throwable? = null
) : CacheLoadException(key)