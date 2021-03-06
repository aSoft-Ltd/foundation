import org.jetbrains.compose.compose

plugins {
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

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.liveCore)
                implementation(compose.runtime)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "Bindings for Live<S> object to be used with compose"
)