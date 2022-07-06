package viewmodel.internal

import koncurrent.Executor
import kotlinx.serialization.StringFormat
import logging.Logger
import viewmodel.StatefulViewModelConfig

internal class StatefulViewModelConfigImpl<out A, out S>(
    override val api: A,
    override val state: S,
    override val executor: Executor,
    override val codec: StringFormat,
    override val logger: Logger
) : StatefulViewModelConfig<A, S> {
    override fun <R> map(transformer: (A) -> R) = StatefulViewModelConfigImpl(
        api = transformer(api), state, executor, codec, logger
    )

    override fun <T> of(state: T) = StatefulViewModelConfigImpl(
        api, state, executor, codec, logger
    )
}