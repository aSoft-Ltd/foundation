package koncurrent.later.internal

import koncurrent.Later

internal class LaterQueueComponent<T>(
    val controlledLater: Later<T>,
    val fulfilledFn: ((Any?) -> T?)?,
    val rejectedFn: ((Throwable) -> T?)?
)