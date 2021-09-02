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
            create("asoft") {
                from(files("../gradle/asoft.versions.toml"))
            }
            create("plugs") {
                from(files("../gradle/plugs.versions.toml"))
            }
            create("kotlinx") {
                from(files("../gradle/kotlinx.versions.toml"))
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

include(":kotlinx-coroutines-core")
project(":kotlinx-coroutines-core").projectDir = File("kotlinx/coroutines/core")

include(":kotlinx-coroutines-test")
project(":kotlinx-coroutines-test").projectDir = File("kotlinx/coroutines/test")

include(":kotlinx-collections")
project(":kotlinx-collections").projectDir = File("kotlinx/collections")