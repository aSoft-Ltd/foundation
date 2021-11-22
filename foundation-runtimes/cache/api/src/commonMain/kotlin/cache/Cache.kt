package cache

import cache.exceptions.CacheLoadException
import cache.exceptions.CacheSaveException
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import later.Later
import later.await
import later.later

/**
 * An interface to be able to [Cache] different objects
 */
abstract class Cache(open val config: CacheConfiguration) {
    /**
     * Should return the set of all available keys in the [Cache]
     */
    abstract fun keys(): Later<Set<String>>

    @PublishedApi
    internal open val scope
        get() = config.scope

    /**
     * Should return the size of the [Cache] which should ideally equal the number of [keys]
     */
    abstract fun size(): Later<Int>

    /**
     * Save object [T] on to the [Cache] with a [key] and its serializer [serializer]
     *
     * @return a [Later] that
     * - on success: resolves the saved object as it was cached
     * - on failure: rejects with a [CacheSaveException]
     */
    abstract fun <T> save(key: String, obj: T, serializer: KSerializer<T>): Later<T>

    /**
     * Load object [T] from the [Cache], that was saved with a [key] and its serializer [serializer]
     *
     * @return a [Later] that
     * - on success: resolves to the cached object
     * - on failure: rejects with a [CacheLoadException]
     */
    abstract fun <T> load(key: String, serializer: KSerializer<T>): Later<T>

    /**
     * Save object [T] on to the [Cache] with a [key]
     *
     * @see [save]
     *
     * @return a [Later] that
     * - on success: resolves the saved object as it was cached
     * - on failure: rejects with a [CacheSaveException]
     */
    inline fun <reified T> save(key: String, obj: T): Later<T> = scope.later {
        try {
            save(key, obj, serializer()).await()
        } catch (e: Throwable) {
            throw CacheSaveException(cause = e)
        }
    }

    /**
     * Save object [T] on to the [Cache] with a [key] and an optional [serializer]
     *
     * @see [Cache.save]
     *
     * @return [Later] that
     * - on success: resolves the saved object as it was cached
     * - on failure: resolves with a null
     */
    inline fun <reified T> saveOrNull(
        key: String,
        obj: T,
        serializer: KSerializer<T>? = null
    ): Later<T?> = scope.later {
        try {
            save(key, obj, serializer ?: serializer()).await()
        } catch (e: Throwable) {
            null
        }
    }

    /**
     * Load object [T] from the [Cache], that was saved with a [key] with an optional serializer [serializer]
     *
     * @see [load]
     *
     * @return [Later] that
     * - on success: resolves the saved object as it was cached
     * - on failure: resolves with a null
     */
    inline fun <reified T> load(key: String): Later<T> = scope.later {
        try {
            load<T>(key, serializer()).await()
        } catch (e: Throwable) {
            throw CacheLoadException(key, cause = e)
        }
    }

    /**
     * Load object [T] from the [Cache] with a [key] and an optional [serializer]
     *
     * @see [Cache.load]
     *
     * @return a [Later] that
     * - on success: resolves the saved object as it was cached
     * - on failure: resolves with a null
     */
    inline fun <reified T> loadOrNull(
        key: String,
        serializer: KSerializer<T>? = null
    ): Later<T?> = scope.later {
        try {
            load(key, serializer ?: serializer()).await()
        } catch (e: Throwable) {
            null
        }
    }
}