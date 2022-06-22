package logging

import kotlin.js.JsName

class Logger(vararg appenders: Appender) : Appender {
    private val appenders = appenders.toList()

    private val extra = mutableMapOf<String, Any?>()

//    @JsName("_ignore_withExtraPair")
    fun with(vararg data: Pair<String, Any?>): Logger = Logger(*appenders.toTypedArray()).apply {
        extra.putAll(this@Logger.extra)
        extra.putAll(data.toMap())
    }

//    @JsName("_ignore_withExtraMap")
    fun with(map: Map<String, Any?>): Logger = with(*map.toList().toTypedArray())

    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        appenders.forEach { it.append(level, msg, *(extra.toList() + data.toList()).toTypedArray()) }
    }

    override fun append(vararg o: Any?) = appenders.forEach { it.append(o) }
}