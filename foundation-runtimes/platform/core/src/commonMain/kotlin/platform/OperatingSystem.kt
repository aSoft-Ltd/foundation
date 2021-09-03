package platform

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class OperatingSystem(
    val family: String,
    val version: String
)