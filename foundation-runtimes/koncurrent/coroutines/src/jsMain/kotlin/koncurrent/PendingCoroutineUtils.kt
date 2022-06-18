@file:Suppress("PackageDirectoryMismatch")

package koncurrent.coroutines

import koncurrent.*
import kotlinx.coroutines.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
//
//internal actual suspend inline fun <T> Pending<T>.completeSuspending(noinline finalizer: suspend (Settled<T>) -> Unit): Pending<T> {
//    coroutineScope {
//        then(
//            onFulfilled = { finalizer(Fulfilled(it)) },
//            onRejected = { finalizer(Rejected(it)) }
//        )
//    }
//    return this
//}