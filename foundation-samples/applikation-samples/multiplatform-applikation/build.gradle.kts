plugins {
    id("com.android.application")
    kotlin("multiplatform")
    id("tz.co.asoft.applikation")
}

android {
    configureAndroid("src/androidMain")
    defaultConfig {
        minSdk = 18
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = "debug"
            setManifestPlaceholders(mutableMapOf("appName" to "Konfig Test Debug"))
            isMinifyEnabled = false
        }
        val staging by creating {
            applicationIdSuffix = "staging"
            initWith(debug)
            isMinifyEnabled = false
            setManifestPlaceholders(mutableMapOf("appName" to "Konfig Test Staging"))
            setMatchingFallbacks("release")
        }
        val release by getting {
            initWith(debug)
            isMinifyEnabled = false
            setManifestPlaceholders(mutableMapOf("appName" to "Konfig"))
            setMatchingFallbacks("release")
        }
    }
}

group = "tz.co.asoft"
version = "2021.2"

applikation {
    common(
        "Main-Class" to "tz.co.asoft.MainKt"
    )
    debug(
        "change" to 1,
        "authors" to mapOf(
            "architecture" to "andylamax@programmer.net"
        ),
        "link" to "http://debug.com"
    )

    staging(
        "link" to "https://staging.com"
    )

    release(
        "link" to "https://release.com"
    )
}

kotlin {
    android { application() }
    jvm { application() }
    js(IR) { application() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(asoft.applikation.runtime)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft.expect.core)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlinx.coroutines.core)
                implementation(kotlinw.react.core)
                implementation(kotlinw.react.dom.old)
                implementation(kotlinw.styled)
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(asoft.expect.coroutines)
            }
        }
    }
}