package koncurrent

import kotlinx.coroutines.CoroutineScope

fun Executor.asCoroutineScope(): CoroutineScope = when (this) {
    is CoroutineExecutor -> scope
    else -> CoroutineExecutor.default().scope
}