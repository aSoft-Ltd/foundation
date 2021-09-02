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
        gradlePluginPortal()
        mavenCentral()
    }

    dependencyResolutionManagement {
        versionCatalogs {
            create("asoft") {
                from(files("gradle/asoft.versions.toml"))
            }

            create("plugs") {
                from(files("gradle/plugs.versions.toml"))
            }
        }
    }
}

rootProject.name = "foundation"

include(":foundation-plugins")