package kuest

import koncurrent.Executor

internal class HttpClientMockConfigImpl(
    override val executor: Executor,
    override val resolve: (HttpRequest<*>) -> Any?
) : HttpClientMockConfig