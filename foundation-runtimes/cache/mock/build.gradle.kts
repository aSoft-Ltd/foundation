plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    //    nativeTargets(true) // removed because iosArm64 was not being supported by okio
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.cacheApi)
                api(projects.cacheFile)
                api(projects.koncurrentPrimitivesMock)
                api(projects.kotlinxCollectionsAtomic)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.cacheTest)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.foundation.get(),
    description = "An implementation of the cache-api to help caching simple objects in memory"
)