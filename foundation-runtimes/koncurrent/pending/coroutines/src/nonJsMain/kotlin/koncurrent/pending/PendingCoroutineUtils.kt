@file:JvmName("PendingCoroutineUtilsJvm")

package koncurrent.pending

import koncurrent.Fulfilled
import koncurrent.Pending
import koncurrent.Rejected
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.jvm.JvmName


@PublishedApi
internal suspend inline fun <T> Pending<Pending<T>>.awaitChainCallback(): T = suspendCancellableCoroutine { cont: Continuation<T> ->
    complete { outerState ->
        when (outerState) {
            is Fulfilled -> outerState.value.complete { innerState ->
                when (innerState) {
                    is Fulfilled -> cont.resume(innerState.value)
                    is Rejected -> cont.resumeWithException(innerState.cause)
                }
            }
            is Rejected -> cont.resumeWithException(outerState.cause)
        }
    }
}

@PublishedApi
internal suspend inline fun <T> Pending<Pending<T>>.awaitChainSuspend(): T = await().await()
actual suspend inline fun <T> Pending<Pending<T>>.awaitChain(): T = awaitChainSuspend()