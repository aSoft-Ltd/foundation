import expect.expect
import viewmodel.CounterViewModel
import viewmodel.CounterViewModel.State
import viewmodel.ViewModel
import viewmodel.expect
import kotlin.test.Test

/**
 * Given a [ViewModel] [CounterViewModel]
 */
class ViewModelIntentTest {
    @Test
    fun state_should_follow_intents() {
        val vm = CounterViewModel()
        expect(vm.countState).toBe(0)

        vm.expect {
            countUp(by = 1)
        }.toGoThrough(State(1))


        vm.expect {
            countUp(2)
        }.toGoThrough(State(2), State(3))
    }
}