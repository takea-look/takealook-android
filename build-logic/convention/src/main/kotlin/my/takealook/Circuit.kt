package my.takealook

import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

fun Project.configureCircuit() {
    with(pluginManager) {
        apply("com.google.devtools.ksp")
        apply("org.jetbrains.kotlin.plugin.parcelize")
    }

    extensions.configure<KspExtension> {
        arg("circuit.codegen.mode", "hilt")
    }

    dependencies {
        add("implementation", libs.findLibrary("circuit-foundation").get())
        add("implementation", libs.findLibrary("circuit-circuitx-effects").get())
        add("implementation", libs.findLibrary("circuit-codegen-annotations").get())
        add("ksp", libs.findLibrary("circuit-codegen-compiler").get())
    }
}
