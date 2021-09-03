import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin

plugins {
    kotlin("js")
    id("tz.co.asoft.applikation")
}

repositories {
    google()
    jcenter()
}

group = "tz.co.asoft"
version = "2020.2"

applikation {
    debug(
        "link" to "http://debug.com"
    )

    staging(
        "link" to "https://staging.com"
    )

    release(
        "link" to "https://release.com"
    )
}

//rootProject.plugins.withType(NodeJsRootPlugin::class.java) {
//    rootProject.the<NodeJsRootExtension>().versions.webpackDevServer.version = "4.1.0"
//}

kotlin {
    js(IR) {
        browserApp(testTimeout = 10000)
    }

    sourceSets {
        val main by getting {
            dependencies {
                implementation(asoft.applikation.runtime)
                implementation(kotlinx.coroutines.core)
                implementation(kotlinw.css)
                implementation(kotlinw.styled)
                implementation(kotlinw.react.core)
                implementation(kotlinw.react.dom)
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }
    }
}