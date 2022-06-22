package koncurrent.later

import koncurrent.Executor
import koncurrent.Later

internal actual fun <T> Later<T>.toPlatformConcurrentMonad(): Later<T> = this

internal actual fun <T> Later<T>.toPlatformConcurrentMonad(executor: Executor): Later<T> = then(executor, onResolved = { it })