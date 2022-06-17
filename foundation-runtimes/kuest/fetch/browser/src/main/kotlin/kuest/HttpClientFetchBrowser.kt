package kuest

import kuest.internal.globalThis

class HttpClientFetchBrowser : HttpClientFetch(globalThis) {
    override fun toString(): String = "HttpClientFetchBrowser"
}