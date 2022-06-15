@file:JvmName("LaterUtilsJvm")

package koncurrent

import java.util.concurrent.CompletableFuture

fun <T> CompletableFuture<T>.asLater(): Later<T> = Later { resolve, reject ->
    whenComplete { value: T?, err: Throwable? ->
        when {
            value != null && err != null -> resolve(value)
            value != null && err == null -> resolve(value)
            value == null && err != null -> reject(err)
            value == null && err == null -> reject(IllegalStateException("Completable future didn't return with value or exception"))
        }
    }
}

internal actual fun <T> Later<T>.toNativeImplementation(): Pending<T> {
    val future = CompletableFuture<T>()
    then(
        executor = executor,
        onResolved = { future.complete(it) },
        onRejected = { future.completeExceptionally(it) }
    )
    return future
}

internal actual fun <T> Later<T>.toNativeImplementation(executor: Executor): Pending<T> {
    val future = CompletableFuture<T>()
    then(
        executor = executor,
        onResolved = { future.complete(it) },
        onRejected = { future.completeExceptionally(it) }
    )
    return future
}