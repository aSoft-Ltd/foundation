package functions

expect fun interface BiConsumer<T, U> {
    fun accept(t: T, u: U)
}