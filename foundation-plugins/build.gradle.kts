plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
//    `maven-publish`
    alias(plugs.plugins.publish)
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

gradlePlugin {
    plugins {
        val applikation by creating {
            id = "tz.co.asoft.applikation"
            implementationClass = "plugins.ApplikationGradlePlugin"
        }

        val deploy by creating {
            id = "tz.co.asoft.deploy"
            description = "A gradle extension to deploy to sonatype"
            implementationClass = "plugins.DeployToSonatypePlugin"
        }

        val library by creating {
            id = "tz.co.asoft.library"
            description = "A kotlin library plugin"
            implementationClass = "plugins.LibraryPlugin"
        }
    }
}

group = "tz.co.asoft"
version = asoft.versions.foundation.get()

pluginBundle {
    website = "https://github.com/aSoft-Ltd/foundation/tree/master/foundation-plugins"
    vcsUrl = website
    description = "Simple Plugins to Ease Library Development"

    mavenCoordinates {
        groupId = project.group.toString()
        artifactId = project.name
        version = project.version.toString()
    }

    plugins {
        val applikation by getting {
            displayName = "Applikation Plugin"
            tags = listOf("kotlin", "application", "frontend")
        }

        val deploy by getting {
            displayName = "Deploy Plugin"
            tags = listOf("asoft", "nexus", "deploy")
        }

        val library by getting {
            displayName = "Library Plugin"
            tags = listOf("kotlin", "library")
        }
    }
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
    implementation(plugs.android)
    implementation(plugs.kotlin.core)
    implementation(plugs.kotlin.serialization)
    implementation(plugs.nexus.staging)
    implementation(plugs.nexus.publish)
}
