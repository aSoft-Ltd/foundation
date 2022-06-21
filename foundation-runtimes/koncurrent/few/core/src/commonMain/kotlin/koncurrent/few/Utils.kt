package koncurrent.few

import koncurrent.Executor
import koncurrent.Few
import koncurrent.few.internal.SimpleFewImpl

fun <T> Few<T>.executeOn(executor: Executor) : Few<T> = when(this) {
    is SimpleFewImpl -> SimpleFewImpl(executor, block)
    else -> throw RuntimeException("Instance of few is not supported yet")
}