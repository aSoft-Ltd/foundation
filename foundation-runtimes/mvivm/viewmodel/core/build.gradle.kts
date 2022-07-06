plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    signing
}

kotlin {
    android { library() }
    jvm { library() }
    js(IR) { library() }
    val nativeTargets = nativeTargets(true)
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.loggingConsole)
                api(projects.liveCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.viewmodelTestExpect)
            }
        }

        val androidMain by getting {
            dependencies {
                api(androidx.lifecycle.viewmodel.ktx)
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(projects.loggingTestAndroid)
            }
        }

        val nonAndroidMain by creating {
            dependsOn(commonMain)
        }

        val nonAndroidTest by creating {
            dependsOn(nonAndroidMain)
            dependsOn(commonTest)
        }

        val jvmMain by getting {
            dependsOn(nonAndroidMain)
        }

        val jsMain by getting {
            dependsOn(nonAndroidMain)
        }

        for (target in nativeTargets) {
            val main by target.compilations.getting {
                defaultSourceSet {
                    dependsOn(nonAndroidMain)
                }
            }

            val test by target.compilations.getting {
                defaultSourceSet {
                    dependsOn(main.defaultSourceSet)
                }
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "A multiplatfrom library for authoring viewmodel in an MVIVM architecture"
)