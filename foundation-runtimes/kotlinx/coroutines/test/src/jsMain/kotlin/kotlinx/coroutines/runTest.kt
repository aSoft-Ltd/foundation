package kotlinx.coroutines

@Deprecated(
    message = "In favor of runTest from kotlinx.coroutines.tes",
    replaceWith = ReplaceWith(
        expression = "runTest",
        "kotlinx.coroutines.test.runTest"
    )
)
actual fun runTest(dispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> Any?): dynamic = GlobalScope.promise(block = block)