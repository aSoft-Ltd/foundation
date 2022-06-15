package koncurrent

import java.util.concurrent.CompletableFuture

actual fun <T> pending(executor: Executor, block: () -> T): Pending<T> = CompletableFuture.supplyAsync(block, executor)

actual fun <T> pending(block: () -> T): Pending<T> = CompletableFuture.supplyAsync(block)