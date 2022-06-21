package koncurrent.few.internal

import functions.Consumer
import functions.Function
import koncurrent.Executor
import koncurrent.Executors
import koncurrent.Few
import koncurrent.FewCollector

@PublishedApi
internal class SimpleFewImpl<out T>(
    override val executor: Executor = Executors.default(),
    internal val block: FewCollector<T>.() -> Unit
) : Few<T> {
    override fun collect(collector: FewCollector<T>) = executor.execute { collector.block() }

    override fun collect(collector: (T) -> Unit) = collect(SimpleFewCollector(collector))

    override fun executeOn(executor: Executor): Few<T> = SimpleFewImpl(executor, block)

    override fun <R> map(transformer: (T) -> R): Few<R> = transform(transformer)

    override fun <R> map(transformer: Function<T, R>): Few<R> = transform(transformer::invoke)

    override fun onEach(block: (T) -> Unit): Few<T> = transform { block(it);it }

    override fun onEach(consumer: Consumer<@UnsafeVariance T>): Few<T> = transform { consumer.accept(it); it }

    private inline fun <R> transform(
        crossinline transformer: (T) -> R
    ): Few<R> = SimpleFewImpl(executor) {
        collect {
            emit(transformer(it))
        }
    }
}