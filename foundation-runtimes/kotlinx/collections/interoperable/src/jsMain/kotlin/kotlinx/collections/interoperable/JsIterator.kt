package kotlinx.collections.interoperable

interface JsIterator {
    @JsName("next")
    fun next(): IteratorResult
}