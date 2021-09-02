package kotlinx.extensions

import kotlinx.css.*

inline fun CssBuilder.centerContent() {
    textAlign = TextAlign.center
    justifyContent = JustifyContent.center
}

inline fun CssBuilder.centerSelf() {
    justifySelf = JustifyContent.center
}

var CssBuilder.justifyItems: JustifyContent
    inline set(value) = put("justify-items", value.toString())
    inline get() = JustifyContent.valueOf("justify-items")

var CssBuilder.gridArea: String
    inline set(value) = put("grid-area", value)
    inline get() = declarations["grid-area"] as String

var CssBuilder.justifySelf: JustifyContent
    inline set(value) = put("justify-self", value.toString())
    inline get() = JustifyContent.valueOf(declarations["justify-self"].toString())