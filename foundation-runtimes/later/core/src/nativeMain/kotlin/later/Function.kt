package later

actual fun interface Function<T, R> {
    actual fun apply(first: T): R
}