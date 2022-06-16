package koncurrent

import functions.Consumer

fun interface LaterHandler<T> {
    fun execute(resolve: Consumer<T>, reject: Consumer<Throwable>)
}
