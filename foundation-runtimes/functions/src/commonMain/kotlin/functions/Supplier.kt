package functions

fun interface Supplier<out T> {
    fun get(): T
}