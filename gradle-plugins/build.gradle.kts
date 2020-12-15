plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.12.0"
}

repositories {
    mavenLocal()
    google()
    jcenter()
    mavenCentral()
}

gradlePlugin {
    plugins {
        val applikation by creating {
            id = "tz.co.asoft.applikation"
            implementationClass = "tz.co.asoft.ApplikationGradlePlugin"
        }

        val library by creating {
            id = "tz.co.asoft.library"
            description = "A kotlin library plugin"
            implementationClass = "tz.co.asoft.LibraryPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/aSoft-Ltd/builders"
    vcsUrl = website
    description = "Simple Plugins to Ease Library Development"

    plugins {
        val applikation by getting {
            displayName = "Applikation Plugin"
            tags = listOf("kotlin", "application", "frontend")
        }

        val library by getting {
            displayName = "A Kotlin Library Plugin"
            tags = listOf("kotlin", "library")
        }
    }
}

group = "tz.co.asoft"
version = versions.asoft_builders

object versions {
    val kotlin = "1.4.21"
    val asoft_builders = "1.2.0"
    val agp = "4.1.0"
    val nexus_staging = "0.22.0"
}

defaultTasks("jar")

val sourcesJar by tasks.creating(org.gradle.jvm.tasks.Jar::class) {
    archiveClassifier.value("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.creating(org.gradle.jvm.tasks.Jar::class) {
    archiveClassifier.value("javadoc")
}

artifacts {
    archives(sourcesJar)
}

dependencies {
    api("com.android.tools.build:gradle:${versions.agp}")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
    api("org.jetbrains.kotlin:kotlin-serialization:${versions.kotlin}")
    api("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:${versions.nexus_staging}")
}
