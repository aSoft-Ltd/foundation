package cache.exceptions

sealed class CacheException(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)
