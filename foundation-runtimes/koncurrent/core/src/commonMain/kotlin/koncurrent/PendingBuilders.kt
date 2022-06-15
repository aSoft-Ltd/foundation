package koncurrent

expect fun <T> pending(executor: Executor, block: () -> T): Pending<T>

expect fun <T> pending(block: () -> T): Pending<T>