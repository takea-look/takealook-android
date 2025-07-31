package my.takealook

import org.gradle.api.Project

fun Project.configureNavigation3() {
    dependencies.apply {
        val nav3Bundle = libs.findBundle("navigation3").get()
        add("implementation", nav3Bundle)
    }
}
