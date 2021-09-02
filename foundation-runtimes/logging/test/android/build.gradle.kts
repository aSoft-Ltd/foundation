plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    target {
        library()
    }

    sourceSets {
        val test by getting {
            dependencies {
                implementation(kotlin("test-junit5"))
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "Log on android tests without worrying about the android sdk"
)