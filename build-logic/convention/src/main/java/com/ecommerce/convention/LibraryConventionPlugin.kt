package com.ecommerce.convention

import com.android.build.api.dsl.LibraryExtension
import com.ecommerce.convention.utils.applyDefaultConfig
import com.ecommerce.convention.utils.intVersion
import com.ecommerce.convention.utils.libs
import com.ecommerce.convention.utils.setupCompileOptions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

open class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = project.run {
        pluginManager.apply("com.android.library")
        pluginManager.apply("org.jetbrains.kotlin.android")
        pluginManager.apply("io.gitlab.arturbosch.detekt")
        dependencies {
            "detektPlugins"(libs.findLibrary("detekt.compose").get())
            "detektPlugins"(libs.findLibrary("detekt.formatting").get())
        }
        extensions.configure<LibraryExtension> {
            compileSdk = intVersion("compileSdk")
            defaultConfig {
                applyDefaultConfig(
                    intVersion("minSdk"),
                    intVersion("targetSdk")
                )
            }
            setupCompileOptions(tasks)
        }
    }
}
