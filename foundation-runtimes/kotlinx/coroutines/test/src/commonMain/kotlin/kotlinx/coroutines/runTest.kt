package kotlinx.coroutines

@Deprecated(
    message = "In favor of runTest from kotlinx.coroutines.tes",
    replaceWith = ReplaceWith(
        expression = "runTest",
        "kotlinx.coroutines.test.runTest"
    )
)
expect fun runTest(dispatcher: CoroutineDispatcher = Dispatchers.Default, block: suspend CoroutineScope.() -> Any?)