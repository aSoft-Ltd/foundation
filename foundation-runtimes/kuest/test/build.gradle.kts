plugins {
    kotlin("multiplatform")
    id("tz.co.asoft.library")
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
                api(projects.expectCoroutines)
                api(projects.koncurrentCoroutines)
            }
        }
    }
}