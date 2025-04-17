package com.antipov.convention

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = project.run {
        pluginManager.apply("com.android.application")
        pluginManager.apply("org.jetbrains.kotlin.android")
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        extensions.configure<ApplicationExtension> {
            compileSdk = intVersion("compileSdk")
            defaultConfig {
                applyDefaultConfig(
                    intVersion("minSdk"),
                    intVersion("targetSdk")
                )
                versionCode = 1
                versionName = "1.0"
            }

            setupCompileOptions(tasks)
            setupBuildTypes()
            enableCompose()
        }
    }
}
