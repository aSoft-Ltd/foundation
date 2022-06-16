package koncurrent

import kotlinx.coroutines.CoroutineScope

fun CoroutineScope.asExecutor(): CoroutineExecutor = CoroutineExecutor(this)