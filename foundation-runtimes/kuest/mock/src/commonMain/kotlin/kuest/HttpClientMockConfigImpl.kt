package kuest

import koncurrent.Executor

internal class HttpClientMockConfigImpl(
    override val executor: Executor,
    override val interceptor: (String) -> Any
) : HttpClientMockConfig