package kuest

import koncurrent.Pending
import koncurrent.pending.flatten
import koncurrent.pending.then

inline fun Pending<HttpResponse>.bodyAsText(): Pending<String> = then { it.text() }.flatten()