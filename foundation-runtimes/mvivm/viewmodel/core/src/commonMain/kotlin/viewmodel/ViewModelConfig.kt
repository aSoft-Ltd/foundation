@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package viewmodel

import koncurrent.Executor
import koncurrent.SynchronousExecutor
import logging.ConsoleAppender
import logging.Logger
import viewmodel.internal.StatefulViewModelConfigImpl
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.*

@JsExport
interface ViewModelConfig<out A> {
    val api: A
    val executor: Executor
    val logger: Logger

    fun <R> map(transformer: (A) -> R): ViewModelConfig<R>

    fun <S> of(state: S): StatefulViewModelConfig<A, S>

    companion object {
        @JvmField
        val DEFAULT_LOGGER = Logger(ConsoleAppender())

        @JvmField
        val DEFAULT_EXECUTOR = SynchronousExecutor


        @JsName("ofService")
        @JvmName("ofService")
        @JvmOverloads
        @JvmStatic
        operator fun <A> invoke(
            api: A,
            executor: Executor = DEFAULT_EXECUTOR,
            logger: Logger = DEFAULT_LOGGER
        ): ViewModelConfig<A> = StatefulViewModelConfigImpl(api, Unit, executor, logger)

        @JsName("of")
        @JvmName("of")
        @JvmOverloads
        @JvmStatic
        operator fun invoke(
            executor: Executor = DEFAULT_EXECUTOR,
            logger: Logger = DEFAULT_LOGGER
        ): ViewModelConfig<*> = StatefulViewModelConfigImpl(Unit, Unit, executor, logger)
    }
}