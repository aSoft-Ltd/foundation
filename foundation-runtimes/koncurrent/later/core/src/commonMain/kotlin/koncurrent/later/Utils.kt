@file:JvmName("UtilsCommon")

package koncurrent.later

import koncurrent.*
import kotlin.jvm.JvmName

inline fun <T, R> Later<out T>.then(
    executor: Executor = this.executor,
    noinline onFulfilled: (T) -> R
): Later<out R> = then(onFulfilled, null, executor)

inline fun <T> Later<out T>.catch(
    executor: Executor = this.executor,
    noinline handler: (Throwable) -> T
): Later<out T> = then(null, handler, executor)

fun <T> Later<out T>.finally(
    executor: Executor = this.executor,
    cleanUp: (state: Settled<T>) -> Any?
) = cleanUp(executor = executor, cleanUp = cleanUp)

fun <T, R> Later<out T>.flatten(
    executor: Executor = this.executor,
    onResolved: (T) -> Later<R>
): Later<out R> = flatten(onResolved, executor)

inline fun <T> Later<out Later<out T>>.flatten(executor: Executor = this.executor): Later<out T> = flatten(executor) { it }
