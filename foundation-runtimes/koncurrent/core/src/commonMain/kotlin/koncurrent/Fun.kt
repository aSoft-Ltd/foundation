package koncurrent

expect fun interface Fun<T> {
    fun invoke(obj: T)
}