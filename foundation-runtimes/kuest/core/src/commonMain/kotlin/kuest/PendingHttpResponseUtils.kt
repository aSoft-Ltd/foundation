package kuest

import koncurrent.Later
import koncurrent.later.flatten

inline fun Later<out HttpResponse>.bodyAsText(): Later<out String> = flatten { it.text() }