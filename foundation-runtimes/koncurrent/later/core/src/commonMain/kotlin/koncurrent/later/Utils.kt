@file:JvmName("UtilsCommon")

package koncurrent.later

import koncurrent.Executor
import koncurrent.Later
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