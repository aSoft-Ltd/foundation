package kotlinx.collections.interoperable

class JsListIterator(private val iterator: Iterator<*>) : JsIterator {
    private inline fun <O> jso(builder: O.() -> Unit) = js("{}").unsafeCast<O>().apply(builder)
    override fun next(): IteratorResult = if (iterator.hasNext()) jso {
        value = iterator.next()
        done = false
    } else jso {
        value = undefined
        done = true
    }
}