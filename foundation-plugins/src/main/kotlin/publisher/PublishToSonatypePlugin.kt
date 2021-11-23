package publisher

import org.gradle.api.Plugin
import org.gradle.api.Project
import publishToSonatype

open class PublishToSonatypePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val extension = target.extensions.create("publishToSonatype", PublishToSonatypeExtension::class.java, target)
        target.afterEvaluate {
            publishToSonatype(group = extension.group, version = extension.version)
        }
    }
}