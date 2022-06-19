package kuest

import koncurrent.Pending
import koncurrent.pending.unwrap
import koncurrent.pending.then

inline fun Pending<HttpResponse>.bodyAsText(): Pending<String> = then { it.text() }.unwrap()