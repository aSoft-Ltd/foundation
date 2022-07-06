package viewmodel.internal

import expect.BasicExpectation
import expect.internal.BasicExpectationImpl
import viewmodel.ViewModel
import viewmodel.ViewModelExpectation
import kotlin.test.assertEquals

class ViewModelExpectationImpl<S, V : ViewModel<S>>(
    override val value: V
) : ViewModelExpectation<S, V>, BasicExpectation<V> by BasicExpectationImpl(value) {
    override fun toBeIn(expectedState: S) = assertEquals(expectedState, value.ui.value)
}