import my.takealook.configureCoil
import my.takealook.configureNavigation3
import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureModuleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val designSystem = project(":core:designsystem")
            dependencies.add("implementation", designSystem)
            configureNavigation3()
            configureCoil()
        }
    }
}
