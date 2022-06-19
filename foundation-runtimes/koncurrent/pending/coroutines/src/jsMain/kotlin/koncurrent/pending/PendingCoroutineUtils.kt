package koncurrent.pending

import koncurrent.Pending

actual suspend inline fun <T> Pending<Pending<T>>.awaitChain(): T = unsafeCast<Pending<T>>().await()