@file:Suppress("NOTHING_TO_INLINE", "FunctionName")

package koncurrent

import java.util.concurrent.CompletableFuture

actual inline fun <T> Executor.pending(noinline block: () -> T): Pending<T> = CompletableFuture.supplyAsync(block, this)

actual inline fun <T> pending(noinline block: () -> T): Pending<T> = CompletableFuture.supplyAsync(block)

actual inline fun <T> ResolvedPending(value: T): Pending<T> = CompletableFuture.completedFuture(value)

actual inline fun <T> RejectedPending(error: Throwable): Pending<T> = CompletableFuture.failedFuture(error)

actual inline fun <T> ControlledPending(): Pending<T> = CompletableFuture()