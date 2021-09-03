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
            create("kotlinw") {
                from(files("../gradle/kotlinw.versions.toml"))
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