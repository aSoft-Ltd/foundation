plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    alias(plugs.plugins.publish)
}

repositories {
    google()
    gradlePluginPortal()
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

        val publish by creating {
            id = "tz.co.asoft.publish"
            description = "A gradle extension to publish to sonatype"
            implementationClass = "publisher.PublishToSonatypePlugin"
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

        val publish by getting {
            displayName = "A gradle plugin to ease publishing libs to sonatype"
            tags = listOf("asoft", "nexus", "publish")
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
    implementation(plugs.nexus.publish)
}
