@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package koncurrent

import functions.Consumer
import functions.Function
import koncurrent.few.internal.simpleFew
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

@JsExport
interface Few<out T> {
    val executor: Executor

    @JsName("_ignore_collect")
    fun collect(collector: FewCollector<T>)

    /**
     * To be callable from javascript
     */
    @JvmSynthetic
    fun collect(collector: (T) -> Unit)

    fun executeOn(executor: Executor): Few<T>

    @JvmSynthetic
    fun onEach(block: (T) -> Unit): Few<T>

    /**
     * To make it intuitive for java callers
     */
    @JsName("_ignore_onEach")
    fun onEach(consumer: Consumer<@UnsafeVariance T>): Few<T>

    /**
     * To be callable from kotlin + js
     */
    @JvmSynthetic
    fun <R> map(transformer: (T) -> R): Few<R>


    /**
     * To be callable from kotlin + java
     */
    @JsName("_ignore_map")
    fun <R> map(transformer: Function<T, R>): Few<R>

    companion object {
        @JvmStatic
        fun <T> fewOf(value: T): Few<T> = simpleFew {
            emit(value)
        }

        @JvmOverloads
        @JvmStatic
        fun <T> few(
            block: Consumer<FewCollector<T>>,
            on: Executor = Executors.default(),
        ): Few<T> = simpleFew(on, block::accept)
    }
}