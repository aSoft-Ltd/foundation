package kotlinx.coroutines

import kotlinx.coroutines.universal.Dispatchers

expect val Dispatchers.Test: CoroutineDispatcher
expect fun runTest(dispatcher: CoroutineDispatcher = Dispatchers.Test, block: suspend CoroutineScope.() -> Any?): Unit