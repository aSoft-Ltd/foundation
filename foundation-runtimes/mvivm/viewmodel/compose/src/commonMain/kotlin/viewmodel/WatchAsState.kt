package viewmodel

import androidx.compose.runtime.Composable
import live.watchAsState

@Composable
inline fun <S> ViewModel<*, S>.watchAsState(): S = ui.watchAsState()