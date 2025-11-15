import java.util.Properties

plugins {
    alias(libs.plugins.takealook.android.library)
}

val localProps = Properties()
val localFile = rootProject.file("local.properties")
if (localFile.exists()) {
    localFile.inputStream().use { localProps.load(it) }
}

android {
    defaultConfig {
        buildConfigField(
            "String",
            "API_ENDPOINT",
            "\"" + localProps.getProperty("API_ENDPOINT") + "\""
        )
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.bundles.retrofit)
}