import com.android.build.api.dsl.ApplicationExtension
import com.easternkite.takealook.configureAndroidCompose
import com.easternkite.takealook.configureHilt
import com.easternkite.takealook.configureKotlinAndroid
import com.easternkite.takealook.configureNavigation3
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "org.jetbrains.kotlin.android")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
            configureKotlinAndroid(extension)
            configureHilt()
            configureNavigation3()
        }
    }
}
