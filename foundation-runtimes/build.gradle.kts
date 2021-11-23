plugins {
    alias(plugs.plugins.android.library) apply false
    alias(plugs.plugins.kotlin.multiplatform) apply false
    alias(plugs.plugins.kotlin.serialization) apply false
    alias(plugs.plugins.nexus.staging) apply false
    alias(plugs.plugins.nexus.publish)
    alias(asoft.plugins.publish)
}

deployToSonatype {
    version = asoft.versions.foundation.get()
}