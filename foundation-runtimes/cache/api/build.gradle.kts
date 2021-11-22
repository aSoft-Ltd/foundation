plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    nativeTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":later-ktx"))
                api(kotlinx.serialization.core)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "An API to help caching simple objects"
)