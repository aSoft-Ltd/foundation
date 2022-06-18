package kuest

import koncurrent.Pending
import koncurrent.flatten
import koncurrent.then

inline fun Pending<HttpResponse>.bodyAsText(): Pending<String> = then { it.text() }.flatten()