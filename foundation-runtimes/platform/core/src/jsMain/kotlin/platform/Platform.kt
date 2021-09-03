package platform

import platform.utils.common.Function
import platform.utils.common.UNDEFINED
import platform.utils.common.navigator
import platform.utils.npm.platform

private val browserCheckFunction = Function("try {return this===window;}catch(e){ return false;}")
private val nodeCheckFunction = Function("try {return this===global;}catch(e){return false;}")

private val isRunningInBrowser: Boolean by lazy { browserCheckFunction.asDynamic()() }
private val isRunningInNode: Boolean by lazy { nodeCheckFunction.asDynamic()() }
private val isRunningInReactNative: Boolean by lazy {
    jsTypeOf(navigator) != UNDEFINED && navigator?.product == "ReactNative"
}
private val isRunningInJsdom: Boolean by lazy {
    jsTypeOf(navigator) != UNDEFINED &&
            navigator != null &&
            navigator.userAgent != undefined &&
            jsTypeOf(navigator.userAgent) != UNDEFINED &&
            jsTypeOf(navigator.userAgent.match) != UNDEFINED &&
            navigator.userAgent.match("\\bjsdom\\b") == true
}

actual object Platform : ExecutionEnvironment(
    name = when {
        isRunningInJsdom -> Name.JsDom.name
        isRunningInReactNative -> Name.ReactNative.name
        isRunningInBrowser -> Name.Browser.name
        isRunningInNode -> Name.Node.name
        else -> "Javascript"
    },
    runner = Runner(
        name = platform.name,
        version = platform.version
    ),
    os = OperatingSystem(
        family = platform.os?.toString() ?: "Unknown",
        version = platform.os?.version ?: "Unknown"
    )
) {
    val isBrowser = isRunningInBrowser
    val isReactNative = isRunningInReactNative
    val isNode = isRunningInNode
    val isJsdom = isRunningInJsdom
}

