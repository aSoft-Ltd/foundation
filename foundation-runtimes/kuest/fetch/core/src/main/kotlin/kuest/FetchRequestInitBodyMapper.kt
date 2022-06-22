package kuest

open class FetchRequestInitBodyMapper {
    open fun map(body: HttpRequestBody): dynamic = when (body) {
        is EmptyRequestBody -> undefined
        is StringRequestBody -> body.value
        else -> undefined
    }
}