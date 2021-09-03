package kotlinx.coroutines

actual fun runTest(dispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Any?): Unit = GlobalScope.promise(block = block).unsafeCast<dynamic>()

private fun te() {
    Dispatchers.Main
}