package koncurrent.later

import koncurrent.*

inline fun Later<*>.test(executor: Executor = Executors.default()): LaterTestResult = runLaterTest(executor) {
    this
}