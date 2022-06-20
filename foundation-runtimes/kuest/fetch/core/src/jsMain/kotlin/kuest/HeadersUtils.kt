@file:Suppress("NOTHING_TO_INLINE")

package kuest

import kotlin.js.Json

internal inline fun Headers.toJson(): Json {
    val res: Json = js("{}")
    for ((k, v) in entries()) {
        res[k] = v.joinToString(",")
    }
    return res
}