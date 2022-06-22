package koncurrent.pending

import koncurrent.Pending
import koncurrent.later.await as laterAwait

actual suspend inline fun <T> Pending<T>.await(): T = laterAwait()