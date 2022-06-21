package functions

actual fun interface BiConsumer<T, U> {
    actual fun accept(t: T, u: U)
}