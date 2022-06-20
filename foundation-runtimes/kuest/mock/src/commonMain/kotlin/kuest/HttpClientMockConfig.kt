package kuest

import koncurrent.Executor
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic

interface HttpClientMockConfig {
    val executor: Executor
    val interceptor: (HttpRequest<*>) -> Any

    companion object {
        @JvmStatic
        @JvmName("create")
        operator fun invoke(
            executor: Executor,
            interceptor: (HttpRequest<*>) -> Any
        ): HttpClientMockConfig = HttpClientMockConfigImpl(executor, interceptor)
    }
}