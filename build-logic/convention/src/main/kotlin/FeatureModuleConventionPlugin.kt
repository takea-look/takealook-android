import org.gradle.api.Plugin
import org.gradle.api.Project

class FeatureModuleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val designSystem = project(":core:designsystem")
            dependencies.add("implementation", designSystem)
        }
    }
}
