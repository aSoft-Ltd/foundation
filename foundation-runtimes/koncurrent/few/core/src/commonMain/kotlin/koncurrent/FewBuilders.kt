package koncurrent

import koncurrent.few.internal.simpleFew

inline fun <T> fewOf(value: T): Few<T> = simpleFew {
    emit(value)
}

inline fun <T> few(noinline block: FewCollector<T>.() -> Unit): Few<T> = simpleFew(block)