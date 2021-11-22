package cache.exceptions

open class CacheLoadException(
    open val key: Any,
    override val message: String = "Failed to load object with key=$key from the cache",
    override val cause: Throwable? = null
) : CacheException(message, cause)