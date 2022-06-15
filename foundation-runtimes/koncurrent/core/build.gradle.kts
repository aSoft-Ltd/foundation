plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm {
        library()
        withJava()
    }
    js(IR) { library() }

    val nativeTargets = nativeTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.expectCore)
            }
        }

        val nativeMain by creating {
            dependsOn(commonMain)
            dependencies {
                api(projects.laterCore)
            }
        }

        (nativeTargets).forEach {
            val main by it.compilations.getting {}
            main.defaultSourceSet {
                dependsOn(nativeMain)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "An multiplatform representation of a Promised based api"
)