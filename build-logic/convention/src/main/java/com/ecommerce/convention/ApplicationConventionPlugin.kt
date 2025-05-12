package com.ecommerce.convention

import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationExtension
import com.ecommerce.convention.utils.applyDefaultConfig
import com.ecommerce.convention.utils.enableCompose
import com.ecommerce.convention.utils.intVersion
import com.ecommerce.convention.utils.setupCompileOptions
import com.ecommerce.convention.utils.setupLint
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.FileInputStream
import java.util.Properties

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = project.run {
        pluginManager.apply("com.android.application")
        pluginManager.apply("org.jetbrains.kotlin.android")

        extensions.configure<ApplicationExtension> {
            compileSdk = intVersion("compileSdk")
            defaultConfig {
                applicationId = "com.ecommerce"
                applyDefaultConfig(
                    intVersion("minSdk"),
                    intVersion("targetSdk")
                )
                versionCode = 1
                versionName = "1.0"
            }
            setupLint()
            setupSigningConfigs(project)
            setupBuildTypes()
            setupCompileOptions(tasks)
            enableCompose(pluginManager)
        }
    }
}

private fun ApplicationExtension.setupSigningConfigs(project: Project) {
    // According to security best practices, folder certificates/*
    // must be included to .gitignore, to prevent possible leaking passwords and keys.
    // However, as it pet-project application, I will keep it included to VCS.
    val debugProps = project.properties("certificates/signing-debug.properties")
    val releaseProps = project.properties("certificates/signing-release.properties")
    signingConfigs {
        getByName("debug") {
            applySigningProperties(project, debugProps)
        }
        create("release") {
            applySigningProperties(project, releaseProps)
        }
    }
}

private fun ApplicationExtension.setupBuildTypes() {
    buildTypes {
        debug {
            isMinifyEnabled = false
            versionNameSuffix = "-DEBUG"
            signingConfig = signingConfigs.getByName("debug")
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

private fun ApkSigningConfig.applySigningProperties(project: Project, props: Properties) {
    storeFile = project.rootProject.file(props["storeFile"] as String)
    storePassword = props["storePassword"] as String
    keyAlias = props["keyAlias"] as String
    keyPassword = props["keyPassword"] as String
}

private fun Project.properties(name: String): Properties = Properties().apply {
    rootProject.file(name)
        .run(::FileInputStream)
        .run(::load)
}
