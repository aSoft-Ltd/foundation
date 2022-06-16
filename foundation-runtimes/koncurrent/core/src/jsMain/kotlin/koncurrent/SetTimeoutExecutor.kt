package koncurrent

import functions.Runnable

class SetTimeoutExecutor private constructor() : Executor {
    override fun execute(runnable: Runnable) {
        setTimeout({ runnable.run() })
    }

    companion object {
        private val instance by lazy { SetTimeoutExecutor() }
        fun commonPool() = instance
    }
}