@file:Suppress("NOTHING_TO_INLINE")

package koncurrent

import java.util.concurrent.CompletableFuture

actual inline fun <T> Executor.pending(noinline block: () -> T): Pending<T> = CompletableFuture.supplyAsync(block, this)

actual inline fun <T> pending(noinline block: () -> T): Pending<T> = CompletableFuture.supplyAsync(block)

actual inline fun <T> ControlledPending(): Pending<T> = CompletableFuture()