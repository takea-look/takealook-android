import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.easternkite.takealook.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("takealook.android.application.compose") {
            id = "takealook.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        create("takealook.android.library.compose") {
            id = "takealook.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        create("takealook.android.library") {
            id = "takealook.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        create("takealook.feature.module") {
            id = "takealook.feature.module"
            implementationClass = "FeatureModuleConventionPlugin"
        }
    }
}
