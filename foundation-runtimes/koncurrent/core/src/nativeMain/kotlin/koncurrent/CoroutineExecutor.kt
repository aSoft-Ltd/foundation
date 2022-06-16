package koncurrent

import functions.Runnable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CoroutineExecutor private constructor() : Executor {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    override fun execute(runnable: Runnable) {
        scope.launch { runnable.run() }
    }

    companion object {
        fun commonPool(): Executor = CoroutineExecutor()
    }
}