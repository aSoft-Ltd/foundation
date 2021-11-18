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
                api(project(":terminal-api"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":expect-core"))
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "A multiplatform logging solution"
)