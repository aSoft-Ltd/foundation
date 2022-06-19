package koncurrent.later.internal

import koncurrent.Later

internal class LaterQueueComponent<T>(
    var controlledLater: Later<T>,
    var fulfilledFn: ((Any?) -> T?)?,
    val rejectedFn: ((Throwable) -> T?)?
)