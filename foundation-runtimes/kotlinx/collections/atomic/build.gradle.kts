plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    nativeTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":kotlinx-collections-interoperable"))
                api(kotlinx.atomicfu)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(project(":expect-coroutines"))
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "A Kotlin Multiplatform Mutable Collections that are thread safe"
)