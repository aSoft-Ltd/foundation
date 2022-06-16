package koncurrent

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <T> CoroutineScope.later(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T
): Later<T> {
    val later = Later<T>()
    launch(context, start) {
        try {
            later.resolveWith(block())
        } catch (err: Throwable) {
            later.rejectWith(err)
        }
    }
    return later
}