package functions

fun interface Function<in T, out R> {
    fun invoke(obj: T): R
}