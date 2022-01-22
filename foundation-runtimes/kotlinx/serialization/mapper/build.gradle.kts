plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    nativeTargets(false)
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinx.serialization.json)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.expectCore)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "A Kotlinx Serialization Extension for mapping between json objects and kotlin maps"
)