package koncurrent.pending

import koncurrent.Pending
import koncurrent.promise.await

actual suspend inline fun <T> Pending<T>.await(): T = await()

actual suspend inline fun <T> Pending<Pending<T>>.awaitChain(): T = unsafeCast<Pending<T>>().await()