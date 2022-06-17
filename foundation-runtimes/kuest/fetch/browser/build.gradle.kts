plugins {
    kotlin("js")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    js(IR) {
        library()
        browser {
            testTask {
                useKarma { useChromiumHeadless() }
            }
        }
    }

    sourceSets {
        val main by getting {
            dependencies {
                api(projects.kuestFetchCore)
            }
        }

        val test by getting {
            dependencies {
                implementation(projects.kuestTest)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "An extension of the kotlin multiplatform http client abstraction layer"
)