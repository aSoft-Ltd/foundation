pluginManagement {
    enableFeaturePreview("VERSION_CATALOGS")
    resolutionStrategy {
//        eachPlugin {
//            if (requested.id.namespace == "com.android") {
//                useModule("com.android.tools.build:gradle:${vers.agp}")
//            }
//        }
    }

//    plugins {
//        id("com.android.library") version vers.agp apply false
//        kotlin("multiplatform") version vers.kotlin apply false
//        id("io.codearte.nexus-staging") version vers.nexus_staging apply false
//        id("com.gradle.plugin-publish") version vers.gradlePluginPublish apply false
//    }

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