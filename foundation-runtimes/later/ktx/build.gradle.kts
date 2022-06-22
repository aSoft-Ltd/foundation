plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    val nativeTargets = nativeTargets(true)
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.laterCore)
                api(kotlinx.coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.expectCoroutines)
            }
        }

        val jsAndNativeMain by creating {
            dependsOn(commonMain)
        }

        val jsMain by getting {
            dependsOn(jsAndNativeMain)
        }

        nativeTargets.forEach {
            val main by it.compilations.getting {}
            main.defaultSourceSet {
                dependsOn(jsAndNativeMain)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "Extensions of the promise based api to be easily used in kotlin"
)