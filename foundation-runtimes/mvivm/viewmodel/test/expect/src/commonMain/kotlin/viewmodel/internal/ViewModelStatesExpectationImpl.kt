package viewmodel.internal

import expect.BasicExpectation
import expect.internal.BasicExpectationImpl
import viewmodel.ViewModelStatesExpectation

class ViewModelStatesExpectationImpl<S>(
    override val value: List<S>
) : ViewModelStatesExpectation<S>, BasicExpectation<List<S>> by BasicExpectationImpl(value)