package kotlinx.coroutines

import kotlinx.coroutines.runBlocking

actual fun runTest(dispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Any?): Unit = runBlocking {
    withContext(dispatcher) { block() }
}