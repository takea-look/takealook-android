package com.easternkite.takealook

import org.gradle.api.Project

fun Project.configureHilt() {
    with(pluginManager) {
        apply("com.google.dagger.hilt.android")
        apply("com.google.devtools.ksp")
    }
    val hilt = libs.findLibrary("hilt-android").get()
    dependencies.add("implementation", hilt)
    val hiltCompiler = libs.findLibrary("hilt-compiler").get()
    dependencies.add("ksp", hiltCompiler)
}