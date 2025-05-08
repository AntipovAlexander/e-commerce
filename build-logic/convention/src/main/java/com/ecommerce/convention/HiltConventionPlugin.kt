package com.ecommerce.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.findPlugin("hilt").get().get().pluginId)
            apply(plugin = libs.findPlugin("ksp").get().get().pluginId)
            dependencies {
                "implementation"(libs.findLibrary("dagger.hilt.android").get())
                "implementation"(libs.findLibrary("dagger.hilt.compose").get())
                "ksp"(libs.findLibrary("dagger.hilt.compiler").get())
            }
        }
    }
}
