package kuest

import koncurrent.Executor
import koncurrent.Executors
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic

interface HttpClientMockConfig {
    val executor: Executor
    val resolve: (HttpRequest<*>) -> Any?

    companion object {
        @JvmStatic
        @JvmName("create")
        operator fun invoke(
            executor: Executor = Executors.default(),
            resolve: (HttpRequest<*>) -> Any?
        ): HttpClientMockConfig = HttpClientMockConfigImpl(executor, resolve)
    }
}