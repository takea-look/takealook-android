import com.android.build.api.dsl.LibraryExtension
import my.takealook.configureAndroidCompose
import my.takealook.configureCoil
import my.takealook.configureHilt
import my.takealook.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "org.jetbrains.kotlin.android")

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
            configureKotlinAndroid(extension)
            configureHilt()
            configureCoil()
        }
    }
}
