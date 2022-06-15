package koncurrent

fun interface LaterHandler<T> {
    fun execute(resolve: Fun<T>, reject: Fun<Throwable>)
}
