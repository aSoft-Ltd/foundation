package koncurrent

actual object Executors {
    actual fun default(): Executor = CoroutineExecutor.commonPool()
}