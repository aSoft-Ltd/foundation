package koncurrent.coroutines

import koncurrent.CoroutineExecutor
import kotlinx.coroutines.CoroutineScope

fun CoroutineScope.asExecutor(): CoroutineExecutor = CoroutineExecutor(this)