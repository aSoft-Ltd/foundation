package koncurrent.few

import koncurrent.Executor
import koncurrent.Few
import koncurrent.few.internal.SimpleFewImpl
import koncurrent.few.internal.simpleFew

fun <T> Few<T>.executeOn(executor: Executor): Few<T> = when (this) {
    is SimpleFewImpl -> SimpleFewImpl(executor, block)
    else -> throw RuntimeException("Instance of few is not supported yet")
}

inline fun <T> Few<T>.onEach(executor: Executor = this.executor, noinline block: (T) -> Unit): Few<T> = transform(executor) {
    block(it)
    it
}

inline fun <T, R> Few<T>.map(
    executor: Executor = this.executor,
    noinline transformer: (T) -> R
): Few<R> = transform(executor, transformer)

@PublishedApi
internal inline fun <T, R> Few<T>.transform(
    executor: Executor = this.executor,
    noinline transformer: (T) -> R
): Few<R> = simpleFew(executor) {
    collect {
        emit(transformer(it))
    }
}