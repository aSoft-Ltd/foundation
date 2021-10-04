package kotlinx.coroutines

actual fun runTest(dispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Any?): dynamic = GlobalScope.promise(block = block)