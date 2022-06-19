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
                api(projects.kuestCore)
                api(projects.koncurrentPendingCoroutines)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.kuestTest)
            }
        }

//        val nonJvmMain by creating {
//            dependsOn(commonMain)
//        }

//        val jsMain by getting {
//            dependsOn(nonJvmMain)
//        }

        val nativeMain by creating {
//            dependsOn(nonJvmMain)
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
    description = "An extension of the kotlin multiplatform http client abstraction layer"
)