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
    nativeTargets(true)
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.kotlinxSerializationMapper)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "A Library to help reading configurations"
)