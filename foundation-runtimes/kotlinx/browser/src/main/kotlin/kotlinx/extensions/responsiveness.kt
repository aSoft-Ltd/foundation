package kotlinx.extensions

import kotlinx.css.CssBuilder
import kotlinx.browser.window

fun CssBuilder.onMobile(width: Int = 480, builder: CssBuilder.() -> Unit) {
    media("only screen and (max-width: ${width}px), only screen and (orientation: portrait)", builder)
}

fun CssBuilder.onDesktop(width: Int = 1224, builder: CssBuilder.() -> Unit) {
    media("only screen and (min-width : ${width}px), only screen and (orientation: landscape)", builder)
}

fun CssBuilder.onPaper(builder: CssBuilder.() -> Unit) = media("print", builder)

val isDesktop get() = window.screen.availWidth > window.screen.availHeight

val isMobile get() = !isDesktop