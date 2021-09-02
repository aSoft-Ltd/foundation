package kotlinx.coroutines

import kotlinx.coroutines.runBlocking
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val TestDispatcher by lazy { Dispatchers.Default }
actual val kotlinx.coroutines.universal.Dispatchers.Test get() = TestDispatcher
actual fun runTest(dispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Any?): Unit = runBlocking {
    withContext(dispatcher) { block() }
}