@file:Suppress("NON_EXPORTABLE_TYPE")

package koncurrent

import kotlin.js.JsExport

@JsExport
interface Few<out T> {
    val executor: Executor
    fun collect(collector: FewCollector<T>)
}