@JsExport
class Calc(val number: Int) {
    val plus = bind(::add)
    fun add(n: Int) = number + n
}

inline fun <F, T> T.bind(func: F): F = func.asDynamic().bind(this)
