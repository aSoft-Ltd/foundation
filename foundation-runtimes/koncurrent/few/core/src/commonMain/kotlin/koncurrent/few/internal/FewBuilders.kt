package koncurrent.few.internal

import koncurrent.Executor
import koncurrent.Executors
import koncurrent.Few
import koncurrent.FewCollector

@PublishedApi
internal inline fun <T> simpleFew(noinline block: FewCollector<T>.() -> Unit): Few<T> = SimpleFewImpl(block = block)


@PublishedApi
internal class SimpleFewImpl<out T>(
    internal val executor: Executor = Executors.default(),
    internal val block: FewCollector<T>.() -> Unit
) : Few<T> {
    override fun collect(collector: FewCollector<T>) = executor.execute { collector.block() }
}