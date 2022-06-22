package later

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

expect fun loadToNextEventLoop(body: () -> Unit)

@OptIn(ExperimentalContracts::class)
internal fun Any?.isThenable(): Boolean {
    contract {
        returns(true) implies (this@isThenable is Later<*>)
    }
    if (this == null) return false
    if (this is Later<*>) return true
    return false
}