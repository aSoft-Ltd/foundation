package platform.utils.npm

@JsModule("platform")
@JsNonModule
internal external val platform: Platform

internal external interface Platform {
    val ua: String?
    val name: String
    val description: String
    val manufacturer: String
    val version: String
    val os: OS?
    val product: String
}

internal external interface OS {
    val architecture: String
    val family: String
    val version: String?
}