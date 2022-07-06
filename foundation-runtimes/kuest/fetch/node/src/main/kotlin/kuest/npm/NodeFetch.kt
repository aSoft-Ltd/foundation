@file:JsModule("node-fetch")
@file:JsNonModule

package kuest.npm

import koncurrent.Promise
import kuest.internal.Response
import org.w3c.fetch.RequestInit

@JsName("default")
external fun nodeFetch(input: dynamic, init: RequestInit = definedExternally): Promise<Response>