package platform.utils.npm

import platform.utils.common.require

internal val platform by lazy { require<Platform>("platform") }

external interface Platform {
    val ua: String?
    val name: String
    val description: String
    val manufacturer: String
    val version: String
    val os: OS?
    val product: String
}

external interface OS {
    val architecture: String
    val family: String
    val version: String?
}