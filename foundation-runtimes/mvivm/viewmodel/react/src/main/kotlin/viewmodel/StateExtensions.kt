package viewmodel

import useLive

inline fun <S> ViewModel<S>.asState() = useLive(ui)