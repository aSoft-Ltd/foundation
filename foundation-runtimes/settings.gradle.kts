pluginManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
    enableFeaturePreview("VERSION_CATALOGS")
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }

    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencyResolutionManagement {
        versionCatalogs {
            val asoft by creating {
                from(files("../gradle/asoft.versions.toml"))
            }

            val plugs by creating {
                from(files("../gradle/plugs.versions.toml"))
            }

            val kotlinx by creating {
                from(files("../gradle/kotlinx.versions.toml"))
            }

            val kotlinw by creating {
                from(files("../gradle/kotlinw.versions.toml"))
            }

            val npm by creating {
                from(files("../gradle/npm.versions.toml"))
            }
        }
    }
}

fun includeRoot(name: String, path: String) {
    include(":$name")
    project(":$name").projectDir = File(path)
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

rootProject.name = "foundation-runtimes"

includeBuild("../foundation-plugins")

includeSubs("expect", "testing/asserters/expect", "core", "coroutines")
includeRoot("applikation-runtime", "applikation")
includeSubs("cache", "cache", "api", "browser", "react-native", "mock")
includeRoot("kotlinx-serialization-mapper", "kotlinx/serialization/mapper")
includeRoot("kotlinx-browser", "kotlinx/browser")
includeSubs("kotlinx-collections", "kotlinx/collections", "atomic", "interoperable")

includeSubs("later", "later", "core", "ktx")
includeRoot("later-test-expect", "later/test/expect")

includeSubs("logging", "logging", "core", "console", "file")
includeRoot("logging-test-android", "logging/test/android")

includeSubs("platform", "platform", "core")