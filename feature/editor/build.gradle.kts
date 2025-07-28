plugins {
    alias(libs.plugins.takealook.android.library.compose)
    alias(libs.plugins.takealook.feature.module)
}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.memento.core)
    implementation(libs.memento.ui)
}