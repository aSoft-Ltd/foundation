package cache.exceptions

open class CacheMissException(
    override val key: String
) : CacheLoadException("Object with key=$key was not found in the cache")