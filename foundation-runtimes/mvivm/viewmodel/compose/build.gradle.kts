import org.jetbrains.compose.compose

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("tz.co.asoft.library")
    signing
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

android {
    configureAndroid("src/androidMain")
}

kotlin {
    android { library() }
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.viewmodelCore)
                api(projects.liveCompose)
                implementation(compose.runtime)
            }
        }

        val androidMain by getting {
            dependencies {
                api(androidx.lifecycle.viewmodel.compose)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "Bindings for ViewModels object to be used with compose"
)