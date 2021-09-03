package kotlinx.coroutines

expect fun runTest(dispatcher: CoroutineDispatcher = Dispatchers.Default, block: suspend CoroutineScope.() -> Any?)