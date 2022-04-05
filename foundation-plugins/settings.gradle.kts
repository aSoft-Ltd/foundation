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
            file("../gradle/versions").listFiles().map { it.nameWithoutExtension }.forEach {
                create(it) { from(files("../gradle/versions/$it.toml")) }
            }
        }
    }
}

rootProject.name = "foundation-plugins"