pluginManagement {
    enableFeaturePreview("VERSION_CATALOGS")

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
        }
    }
}

rootProject.name = "foundation-plugins"