@file:Suppress("PackageDirectoryMismatch")

package logging

import logging.TodoViewModel.State
import viewmodel.ViewModel
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmOverloads

class TodoViewModel @JvmOverloads constructor(
    private val config: ViewModelConfig<*> = ViewModelConfig()
) : ViewModel<State>(config.of(State.Init)) {

    sealed interface State {
        object Init : State
        data class ShowTodo(val todo: Todo) : State
    }

    fun showTodo(todo: Todo) = executor.execute {
        ui.value = State.ShowTodo(todo)
    }

    fun reInit() {
        ui.value = State.Init
    }
}