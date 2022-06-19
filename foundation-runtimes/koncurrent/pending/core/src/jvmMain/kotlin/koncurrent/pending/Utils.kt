@file:JvmName("UtilsJvm") @file:Suppress("NOTHING_TO_INLINE")

package koncurrent.pending

import koncurrent.*
import java.util.function.BiConsumer

actual inline fun <T, R> Pending<T>.then(
    executor: Executor, noinline onResolved: ((T) -> R)
): Pending<R> = thenApplyAsync(onResolved, executor)

actual inline fun <T, R> Pending<T>.then(noinline onResolved: ((T) -> R)): Pending<R> = thenApplyAsync(onResolved)

actual inline fun <T> Pending<T>.catch(executor: Executor, noinline onRejected: (Throwable) -> T): Pending<T> = exceptionallyAsync(onRejected, executor)

actual inline fun <T> Pending<T>.catch(noinline onRejected: (Throwable) -> T): Pending<T> = exceptionally(onRejected)

@PublishedApi
internal inline fun <T> completeConsumer(
    noinline finalizer: (Settled<T>) -> Unit
): BiConsumer<T?, Throwable?> = BiConsumer { value, error ->
    when {
        value != null -> finalizer(Fulfilled(value))
        error != null -> finalizer(Rejected(error))
        else /* value == null && err == null */ -> finalizer(Rejected(IllegalStateException("Completable future didn't return with value or exception")))
    }
}

actual inline fun <T> Pending<T>.complete(executor: Executor, noinline finalizer: (Settled<T>) -> Unit) = whenCompleteAsync(completeConsumer(finalizer), executor)

actual inline fun <T> Pending<T>.complete(noinline finalizer: (Settled<T>) -> Unit) = whenCompleteAsync(completeConsumer(finalizer))

actual inline fun <T> Pending<T>.finally(executor: Executor, noinline finalizer: () -> Unit): Pending<T> = whenCompleteAsync({ _, _ -> finalizer() }, executor)

actual inline fun <T> Pending<T>.finally(noinline finalizer: () -> Unit): Pending<T> = whenComplete { _, _ -> finalizer() }

actual inline fun <T> Pending<T>.resolveWith(value: T) = complete(value)

actual inline fun <T> Pending<T>.rejectWith(exception: Throwable) = completeExceptionally(exception)

actual inline fun <T, R> Pending<Pending<T>>.flatMap(noinline onFulfilled: (T) -> R): Pending<R> {
    val pending = ControlledPending<R>()
    this@flatMap.then(onResolved = { p ->
        p.then {
            try {
                pending.resolveWith(onFulfilled(it))
            } catch (err: Throwable) {
                pending.rejectWith(err)
            }
        }
    })
    return pending
}