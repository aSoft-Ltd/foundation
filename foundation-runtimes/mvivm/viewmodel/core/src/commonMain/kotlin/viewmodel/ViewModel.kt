@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package viewmodel

import live.*
import kotlin.js.JsExport
import kotlin.js.JsName

abstract class ViewModel<out S>(private val config: StatefulViewModelConfig<*, S>) : BaseViewModel(config) {

    val ui: MutableLive<@UnsafeVariance S> = mutableLiveOf(config.state)

    init {
        ui.watch(WatchMode.Eagerly) { log("State at ${it?.toDetailedString}") }
    }

    override fun onCleared() {
        ui.stopAll()
    }
}