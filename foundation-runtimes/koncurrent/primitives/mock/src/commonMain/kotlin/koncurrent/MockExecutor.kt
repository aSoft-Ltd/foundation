package koncurrent

import functions.Runnable

/**
 * A mock executor that doesn't really switch thread, but keeps execution on the current thread that is executing
 */
class MockExecutor(private val config: MockExecutorConfig = MockExecutorConfig()) : Executor {
    constructor(name: String, logOnExecute: Boolean = MockExecutorConfig.DEFAULT_LOG_ON_EXECUTE) : this(MockExecutorConfig(name, logOnExecute))

    override fun execute(runnable: Runnable) {
        if (config.logOnExecute) println("Executing on ${config.name}")
        runnable.run()
    }

    override fun toString(): String = "MockExecutor(name=${config.name})"
}