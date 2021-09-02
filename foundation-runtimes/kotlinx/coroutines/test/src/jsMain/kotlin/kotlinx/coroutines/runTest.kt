package kotlinx.coroutines

import kotlinx.coroutines.universal.Dispatchers

private val TestDispatcher by lazy { Dispatchers.Universal }
actual val Dispatchers.Test get() = TestDispatcher
actual fun runTest(dispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Any?): Unit = GlobalScope.promise(block = block).unsafeCast<dynamic>()