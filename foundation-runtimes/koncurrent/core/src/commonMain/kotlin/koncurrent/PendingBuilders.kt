package koncurrent

import kotlin.jvm.JvmSynthetic

expect fun <T> Executor.pending(block: () -> T): Pending<T>

@JvmSynthetic
expect fun <T> pending(block: () -> T): Pending<T>