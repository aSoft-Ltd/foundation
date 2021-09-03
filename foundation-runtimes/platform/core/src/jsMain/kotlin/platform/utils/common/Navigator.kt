package platform.utils.common

internal external val navigator: Navigator?
internal const val UNDEFINED = "undefined"

external interface Navigator {
    val userAgent: UserAgent
    val product: String
}

external interface UserAgent {
    val match: (String) -> Boolean?
}