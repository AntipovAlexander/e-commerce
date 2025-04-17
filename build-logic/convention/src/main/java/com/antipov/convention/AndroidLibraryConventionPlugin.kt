package com.antipov.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = project.run {
        pluginManager.apply("com.android.library")
        pluginManager.apply("org.jetbrains.kotlin.android")
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        extensions.configure<LibraryExtension> {
            compileSdk = intVersion("compileSdk")
            defaultConfig {
                applyDefaultConfig(
                    intVersion("minSdk"),
                    intVersion("targetSdk")
                )
                consumerProguardFiles("consumer-rules.pro")
            }

            setupCompileOptions(tasks)
            setupBuildTypes()
            enableCompose()
        }
    }
}
