@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package koncurrent

external class Promise<T>(executor: (resolve: (T) -> Unit, reject: (Throwable) -> Unit) -> Unit) {
    fun <S> then(onFulfilled: ((T) -> S)?, onRejected: ((Throwable) -> S)? = definedExternally): Promise<S>

    fun <S> catch(onRejected: (Throwable) -> S): Promise<S>

    fun finally(onFinally: () -> Unit): Promise<T>

    companion object {
        fun <S> all(promise: Array<out Promise<S>>): Promise<Array<out S>>

        fun <S> race(promise: Array<out Promise<S>>): Promise<S>

        fun reject(e: Throwable): Promise<Nothing>

        fun <S> resolve(e: S): Promise<S>
        fun <S> resolve(e: Promise<S>): Promise<S>
    }
}
