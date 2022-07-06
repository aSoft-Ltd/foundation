@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package viewmodel

import kotlin.js.JsExport

@JsExport
interface StatefulViewModelConfig<out A, out S> : ViewModelConfig<A> {
    val state: S
}