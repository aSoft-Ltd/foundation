package cache.exceptions

class CacheSaveException(
    val key: String,
    override val cause: Throwable
) : CacheException("Failed to save object with key `$key` to the cache", cause)