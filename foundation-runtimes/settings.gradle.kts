pluginManagement {
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

rootProject.name = "foundation-runtimes"

includeBuild("../foundation-plugins")

include(":expect-core")
project(":expect-core").projectDir = File("testing/asserters/expect/core")

include(":expect-coroutines")
project(":expect-coroutines").projectDir = File("testing/asserters/expect/coroutines")

include(":applikation-runtime")
project(":applikation-runtime").projectDir = File("applikation")

// kotlinx
include(":kotlinx-serialization-mapper")
project(":kotlinx-serialization-mapper").projectDir = File("kotlinx/serialization/mapper")

include(":kotlinx-browser")
project(":kotlinx-browser").projectDir = File("kotlinx/browser")

include(":kotlinx-coroutines-test")
project(":kotlinx-coroutines-test").projectDir = File("kotlinx/coroutines/test")

include(":kotlinx-collections-atomic")
project(":kotlinx-collections-atomic").projectDir = File("kotlinx/collections/atomic")

// later
include(":later-core")
project(":later-core").projectDir = File("later/core")

include(":later-ktx")
project(":later-ktx").projectDir = File("later/ktx")

include(":later-test-expect")
project(":later-test-expect").projectDir = File("later/test/expect")

// logging
include(":logging-core")
project(":logging-core").projectDir = File("logging/core")

include(":logging-console")
project(":logging-console").projectDir = File("logging/console")

include(":logging-file")
project(":logging-file").projectDir = File("logging/file")

include(":logging-test-android")
project(":logging-test-android").projectDir = File("logging/test/android")

include(":platform-core")
project(":platform-core").projectDir = File("platform/core")