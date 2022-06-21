package koncurrent

import koncurrent.few.internal.simpleFew
import java.util.function.Consumer

object FewBuilder {
    @JvmStatic
    fun <T> fewOf(value: T): Few<T> = simpleFew {
        emit(value)
    }

    @JvmOverloads
    @JvmStatic
    fun <T> few(
        block: Consumer<FewCollector<T>>,
        executor: Executor = Executors.default(),
    ): Few<T> = simpleFew(executor, block::accept)
}