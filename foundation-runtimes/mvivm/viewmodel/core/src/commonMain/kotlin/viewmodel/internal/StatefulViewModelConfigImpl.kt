package viewmodel.internal

import koncurrent.Executor
import logging.Logger
import viewmodel.StatefulViewModelConfig

internal class StatefulViewModelConfigImpl<out A, out S>(
    override val api: A,
    override val state: S,
    override val executor: Executor,
    override val logger: Logger
) : StatefulViewModelConfig<A, S> {
    override fun <R> map(transformer: (A) -> R) = StatefulViewModelConfigImpl(
        api = transformer(api), state, executor, logger
    )

    override fun <T> of(state: T) = StatefulViewModelConfigImpl(
        api, state, executor, logger
    )
}