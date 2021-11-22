plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

android {
    configureAndroid("src/androidMain")
}

kotlin {
    android { library() }
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

        val jsMain by getting {
            dependencies {
                implementation(project(":platform-core"))
                implementation(npm("prompt-sync", npm.versions.prompt.get()))
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "A multiplatform logging solution"
)