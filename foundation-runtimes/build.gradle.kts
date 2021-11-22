plugins {
    alias(plugs.plugins.android.library) apply false
    alias(plugs.plugins.kotlin.multiplatform) apply false
    alias(plugs.plugins.kotlin.serialization) apply false
    alias(plugs.plugins.nexus.staging) apply false
    alias(plugs.plugins.nexus.publish)
}


afterEvaluate {
    group = "tz.co.asoft"
    version = asoft.versions.foundation.get()
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(System.getenv("ASOFT_NEXUS_USERNAME"))
            password.set(System.getenv("ASOFT_NEXUS_PASSWORD"))
        }
    }
}