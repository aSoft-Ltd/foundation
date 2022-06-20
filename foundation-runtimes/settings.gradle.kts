pluginManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencyResolutionManagement {
        versionCatalogs {
            file("../gradle/versions").listFiles().map {
                it.nameWithoutExtension to it.absolutePath
            }.forEach { (name, path) ->
                create(name) { from(files(path)) }
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

includeRoot("functions", "functions")

includeSubs("koncurrent-primitives", "koncurrent/primitives", "core", "coroutines")
includeSubs("koncurrent-later", "koncurrent/later", "core", "coroutines")
includeSubs("koncurrent-pending", "koncurrent/pending", "core", "coroutines")

includeSubs("kuest", "kuest", "http", "core", "coroutines", "mock", "test")
includeSubs("kuest-fetch", "kuest/fetch", "core", "browser", "node")

includeSubs("later", "later", "core", "ktx")
includeRoot("later-test-expect", "later/test/expect")

includeSubs("logging", "logging", "core", "console", "file")
includeRoot("logging-test-android", "logging/test/android")

includeSubs("platform", "platform", "core")