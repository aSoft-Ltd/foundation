import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin

plugins {
    kotlin("js")
    id("tz.co.asoft.applikation")
}

repositories {
    google()
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
//    rootProject.the<NodeJsRootExtension>().versions.apply {
//        webpackDevServer.version = "4.1.0"
//        webpackCli.version = "4.9.0"
//    }
//}

kotlin {
    js(IR) {
        browserApp(testTimeout = 10000)
    }

    sourceSets {
        val main by getting {
            dependencies {
                implementation(asoft.applikation.runtime)
                implementation(project.dependencies.platform(kotlinw.bom))
                implementation(kotlinx.coroutines.core)
                implementation(kotlinw.css)
                implementation(kotlinw.styled)
                implementation(kotlinw.react.core)
                implementation(kotlinw.react.dom.old)
            }
        }

        val test by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }
    }
}