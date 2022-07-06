package viewmodel

import live.watchAsFlow

fun <S> ViewModel<S>.asFlow() = ui.watchAsFlow()