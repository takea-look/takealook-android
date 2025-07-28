import com.android.build.api.dsl.LibraryExtension
import my.takealook.configureAndroidCompose
import my.takealook.configureCoil
import my.takealook.configureHilt
import my.takealook.configureKotlinAndroid
import my.takealook.configureNavigation3
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")

            val extension = extensions.getByType<LibraryExtension>()
            configureKotlinAndroid(extension)
            configureHilt()
        }
    }
}
