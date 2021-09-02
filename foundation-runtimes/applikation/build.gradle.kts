plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("io.codearte.nexus-staging")
    id("tz.co.asoft.library")
    signing
}

android {
    configureAndroid("src/androidMain")
}

kotlin {
    android { library() }
    jvm { library() }
    js(IR) { library() }
    macosX64()
    ios()
    tvos()
    linuxX64()
    linuxArm64()
    linuxArm32Hfp()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":kotlinx-serialization-mapper"))
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "A Library to help reading configurations"
)