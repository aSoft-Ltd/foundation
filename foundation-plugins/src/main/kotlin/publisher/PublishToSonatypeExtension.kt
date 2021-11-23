package publisher

import org.gradle.api.Project

open class PublishToSonatypeExtension(val project: Project) {
    var group = "tz.co.asoft"
    var version = "0.0.0"
}