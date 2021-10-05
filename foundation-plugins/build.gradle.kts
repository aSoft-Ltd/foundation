plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    alias(plugs.plugins.publish)
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        val applikation by creating {
            id = "tz.co.asoft.applikation"
            implementationClass = "builders.ApplikationGradlePlugin"
        }

        val library by creating {
            id = "tz.co.asoft.library"
            description = "A kotlin library plugin"
            implementationClass = "builders.LibraryPlugin"
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
version = asoft.versions.foundation.get()

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
    implementation(plugs.android)
    implementation(plugs.kotlin.core)
    implementation(plugs.kotlin.serialization)
    implementation(plugs.nexus.staging)
}