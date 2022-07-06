@file:JvmName("ExpectBuilders")

package viewmodel

import koncurrent.Executor
import koncurrent.SynchronousExecutor
import kotlinx.collections.atomic.mutableAtomicListOf
import live.WatchMode
import live.watch
import viewmodel.internal.ViewModelExpectationImpl
import viewmodel.internal.ViewModelStatesExpectationImpl
import kotlin.jvm.JvmName

fun <S, V : ViewModel<S>> expect(viewModel: V): ViewModelExpectation<S, V> = ViewModelExpectationImpl(viewModel)

inline fun <S, V : ViewModel<S>> V.expect(
    executor: Executor = SynchronousExecutor,
    builder: V.() -> Unit
): ViewModelStatesExpectation<S> {
    val states = mutableAtomicListOf<S>()
    val watcher = ui.watch(WatchMode.Casually, executor) { states.add(it) }
    builder()
    watcher.stop()
    return ViewModelStatesExpectationImpl(states.toList())
}