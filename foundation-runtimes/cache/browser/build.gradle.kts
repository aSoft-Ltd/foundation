plugins {
    kotlin("js")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    js(IR) { browserLib() }

    sourceSets {
        val main by getting {
            dependencies {
                api(project(":cache-api"))
                api(kotlinx.serialization.json)
            }
        }

        val test by getting {
            dependencies {
                implementation(project(":expect-coroutines"))
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "An implementation of the cache-api to help caching simple objects on the browser"
)