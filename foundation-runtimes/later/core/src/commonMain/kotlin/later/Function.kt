package later

expect fun interface Function<T, R> {
    fun apply(first: T): R
}