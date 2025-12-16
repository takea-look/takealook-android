plugins {
    alias(libs.plugins.takealook.android.library.compose)
    alias(libs.plugins.takealook.feature.module)
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.feature.editor) // TODO : EditorScreen 참조하겠다고 해당 모듈을 통째로 바라보는건 부적절함. api / impl 형태로 모듈을 더 세분화 해야함
}