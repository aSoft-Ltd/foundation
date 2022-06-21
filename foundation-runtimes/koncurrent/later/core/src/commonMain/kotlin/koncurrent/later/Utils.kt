@file:JvmName("UtilsCommon")

package koncurrent.later

import koncurrent.*
import koncurrent.later.internal.PlatformConcurrentMonad
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.jvm.JvmName

@OptIn(ExperimentalContracts::class)
internal fun Any?.isThenable(): Boolean {
    contract {
        returns(true) implies (this@isThenable is Later<*>)
    }
    if (this == null) return false
    if (this is Later<*>) return true
    return false
}

internal expect fun <T> Later<T>.toPlatformConcurrentMonad(executor: Executor): PlatformConcurrentMonad<T>

internal expect fun <T> Later<T>.toPlatformConcurrentMonad(): PlatformConcurrentMonad<T>

fun <T, R> Later<Later<T>>.unwrap(executor: Executor = this.executor, onFulfilled: (T) -> R): Later<R> = when (val s1 = state) {
    is Rejected -> Later.reject(s1.cause, executor) as Later<R>
    is Fulfilled -> {
        val value1 = s1.value
        when (val s2 = value1.state) {
            is Rejected -> Later.reject(s2.cause, executor) as Later<R>
            is Fulfilled -> Later.resolve(onFulfilled(s2.value), executor) as Later<R>
            PendingState -> {
                val later = Later<R>()
                value1.then(
                    executor = executor,
                    onResolved = { later.resolveWith(onFulfilled(it)) },
                    onRejected = { later.rejectWith(it) }
                )
                later
            }
        }
    }
    PendingState -> {
        val later = Later<R>(executor)
        then(
            executor = executor,
            onResolved = { value1 ->
                value1.then(
                    executor = executor,
                    onResolved = { later.resolveWith(onFulfilled(it)) },
                    onRejected = { later.rejectWith(it) }
                )
            },
            onRejected = { later.rejectWith(it) }
        )
        later
    }
}

inline fun <T> Later<Later<T>>.unwrap(executor: Executor = this.executor): Later<T> = unwrap(executor) { it }

inline fun <T, R> Later<T>.then(executor: Executor = this.executor, noinline onFulfilled: (T) -> R): Later<R> = then(executor, onFulfilled, null)