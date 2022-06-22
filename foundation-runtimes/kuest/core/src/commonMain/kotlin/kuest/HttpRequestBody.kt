package kuest

interface HttpRequestBody

object EmptyRequestBody : HttpRequestBody

open class StringRequestBody(val value: String) : HttpRequestBody

class JsonRequestBody(value: String) : StringRequestBody(value)