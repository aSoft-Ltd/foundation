package platform

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
open class ExecutionEnvironment(
    open val name: String,
    open val runner: Runner,
    open val os: OperatingSystem
) {
    @Serializable
    class Runner(
        val name: String,
        val version: String
    )

    sealed class Name(val name: String) {
        object Android : Name("JVM/Android")
        object Browser : Name("JavaScript/Browser")
        object iOS : Name("iOS")
        object JsDom : Name("JavaScript/JSDom")
        object JVM : Name("JVM")
        object Linux : Name("Linux")
        object MacOS : Name("MacOS")
        object Node : Name("JavaScript/Node")
        object ReactNative : Name("JavaScript/ReactNative")
        object TvOS : Name("TvOS")
        object WatchOS : Name("WatchOs")
        object Windows : Name("Windows")
        object UNIX : Name("UNIX")
        object Unknown : Name("Unknown")
    }
}