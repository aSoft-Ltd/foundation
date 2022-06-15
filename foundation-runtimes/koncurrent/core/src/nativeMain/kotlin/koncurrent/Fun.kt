package koncurrent

actual fun interface Fun<T> {
    actual fun invoke(obj: T)
}