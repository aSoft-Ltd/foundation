package kuest

import kuest.npm.NodeFetch

class HttpClientFetchNode : HttpClientFetch(NodeFetch) {
    override fun toString(): String = "HttpClientFetchNode"
}