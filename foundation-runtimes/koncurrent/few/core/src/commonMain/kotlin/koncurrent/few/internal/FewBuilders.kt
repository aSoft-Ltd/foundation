package koncurrent.few.internal

import koncurrent.Executor
import koncurrent.Executors
import koncurrent.Few
import koncurrent.FewCollector

@PublishedApi
internal inline fun <T> simpleFew(on: Executor = Executors.default(), noinline block: FewCollector<T>.() -> Unit): Few<T> = SimpleFewImpl(on, block)


@PublishedApi
internal class SimpleFewImpl<out T>(
    override val executor: Executor = Executors.default(),
    internal val block: FewCollector<T>.() -> Unit
) : Few<T> {
    override fun collect(collector: FewCollector<T>) = executor.execute { collector.block() }
}