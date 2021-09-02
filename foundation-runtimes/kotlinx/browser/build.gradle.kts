plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    js(IR) { library() }
    sourceSets {
        val main by getting {
            dependencies {
                api(kotlinx.coroutines.core)
                api(kotlinx.html)
                api(kotlinw.extension)
                api(kotlinw.css)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "A bunch of helper classes, functions and extensions generally used in kotlin for browser projects"
)