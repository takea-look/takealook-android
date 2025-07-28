package my.takealook

import org.gradle.api.Project

fun Project.configureNavigation3() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.plugin.serialization")
    }
    dependencies.apply {
        val nav3Bundle = libs.findBundle("navigation3").get()
        add("implementation", nav3Bundle)

        val serialization = libs.findLibrary("kotlinx-serialization-core").get()
        add("implementation", serialization)
    }
}
