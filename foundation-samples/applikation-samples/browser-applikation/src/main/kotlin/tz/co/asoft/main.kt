package tz.co.asoft

import kotlinx.browser.document
import react.RBuilder
import react.dom.h3
import react.dom.render
import kotlin.js.json
import applikation.konfig

fun main() {
    console.log("Works")
    val kfg = konfig()
    console.log(json(*kfg.map { (k, v) -> k to v }.toTypedArray()))
    val link: String by kfg
    println("It works with reload: Link found was ${link.capitalize()}")

    render(document.getElementById("root")!!) {
        User("Juma")
        User("Peter")
        User("Andrew")
        Counter(startAt = 5)
    }
}

private fun RBuilder.User(name: String) = h3 { +"User: $name" }