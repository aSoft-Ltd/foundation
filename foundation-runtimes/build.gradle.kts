@Suppress("DSL_SCOPE_VIOLATION") plugins {
    alias(plugs.plugins.android.library) apply false
    alias(plugs.plugins.kotlin.multiplatform) apply false
    alias(plugs.plugins.kotlin.serialization) apply false
    alias(plugs.plugins.nexus.staging) apply false
    alias(plugs.plugins.nexus.publish)
    alias(asoft.plugins.deploy)
}

val tmp = 1

repositories {
    publicRepos()
}

deployToSonatype {
    version = asoft.versions.foundation.get()
}