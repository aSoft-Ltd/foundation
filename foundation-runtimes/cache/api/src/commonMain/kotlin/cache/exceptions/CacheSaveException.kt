package cache.exceptions

class CacheSaveException(
    override val message: String = "Failed to save to the cache",
    override val cause: Throwable
) : CacheException(message, cause)