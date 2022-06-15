package koncurrent

// It's workaround for KT-19672 since we can fix it properly until KT-11265 isn't fixed.
inline fun <T, S> Promise<Promise<T>>.then(
    noinline onFulfilled: ((T) -> S)?
): Promise<S> {
    return this.unsafeCast<Promise<T>>().then(onFulfilled)
}

inline fun <T, S> Promise<Promise<T>>.then(
    noinline onFulfilled: ((T) -> S)?,
    noinline onRejected: ((Throwable) -> S)?
): Promise<S> {
    return this.unsafeCast<Promise<T>>().then(onFulfilled, onRejected)
}
