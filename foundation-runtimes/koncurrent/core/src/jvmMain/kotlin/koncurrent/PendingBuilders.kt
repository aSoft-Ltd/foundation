package koncurrent

import java.util.concurrent.CompletableFuture

@JvmSynthetic
actual fun <T> Executor.pending(block: () -> T): Pending<T> = CompletableFuture.supplyAsync(block, this)

@JvmSynthetic
actual fun <T> pending(block: () -> T): Pending<T> = CompletableFuture.supplyAsync(block)