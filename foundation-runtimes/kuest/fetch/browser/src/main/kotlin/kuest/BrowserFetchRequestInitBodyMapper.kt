package kuest

class BrowserFetchRequestInitBodyMapper : FetchRequestInitBodyMapper() {
    override fun map(body: HttpRequestBody): dynamic {
        val mapped = super.map(body)
        return if (mapped == null || mapped == undefined) {
            when (body) {
                is FormDataRequestBody -> body.value
                else -> undefined
            }
        } else {
            mapped
        }
    }
}