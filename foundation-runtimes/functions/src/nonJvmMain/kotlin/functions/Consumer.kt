package functions

actual fun interface Consumer<T> {
    actual fun accept(obj: T)
}