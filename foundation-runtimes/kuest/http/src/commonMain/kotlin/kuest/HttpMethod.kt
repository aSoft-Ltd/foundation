package kuest

enum class HttpMethod(val value: String) {
    Get("GET"),
    Post("POST"),
    Put("PUT"),
    Patch("PATCH"),
    Delete("DELETE"),
    Head("HEAD"),
    Connect("CONNECT"),
    Trace("TRACE"),
    Options("OPTIONS")
}