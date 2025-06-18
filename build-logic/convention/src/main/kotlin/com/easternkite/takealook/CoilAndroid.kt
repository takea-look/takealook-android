package com.easternkite.takealook

import org.gradle.api.Project

fun Project.configureCoil() {
    val coilCompose = libs.findLibrary("coil-compose").get()
    dependencies.add("implementation", coilCompose)

    val coilNetworkOkhttp = libs.findLibrary("coil-network-okhttp").get()
    dependencies.add("implementation", coilNetworkOkhttp)
}