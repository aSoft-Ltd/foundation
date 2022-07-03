package koncurrent

actual fun <T> runLaterTest(executor: Executor, block: () -> Later<out T>): dynamic {
    return Promise<Unit> { resolve, _ ->
        block().then({ resolve(Unit) })
    }.unsafeCast<LaterTestResult>()
}


internal external fun done(cause: Throwable)