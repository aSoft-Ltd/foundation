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
            file("../gradle/versions").listFiles().map { it.nameWithoutExtension }.forEach {
                create(it) { from(files("../gradle/versions/$it.toml")) }
            }
        }
    }
}

rootProject.name = "foundation-samples"

includeBuild("../foundation-plugins")
includeBuild("../foundation-runtimes")

listOf("android", "browser", "desktop", "multiplatform").forEach {
    include(":applikation-samples:$it-applikation")
}