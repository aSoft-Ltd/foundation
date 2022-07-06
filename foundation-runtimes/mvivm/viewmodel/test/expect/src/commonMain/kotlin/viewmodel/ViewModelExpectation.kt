package viewmodel

import expect.BasicExpectation

interface ViewModelExpectation<S, V : ViewModel<S>> : BasicExpectation<V> {
    fun toBeIn(expectedState: S)
}