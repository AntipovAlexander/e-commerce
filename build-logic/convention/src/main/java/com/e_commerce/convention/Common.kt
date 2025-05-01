package com.e_commerce.convention

import com.android.build.api.dsl.AndroidResources
import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DefaultConfig
import com.android.build.api.dsl.Installation
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.JavaVersion
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

fun <
    A : BuildFeatures,
    B : BuildType,
    C : DefaultConfig,
    D : ProductFlavor,
    E : AndroidResources,
    F : Installation
    > CommonExtension<A, B, C, D, E, F>.setupCompileOptions(
    tasks: TaskContainer
) {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions { jvmTarget.set(JvmTarget.JVM_11) }
    }
}

fun <A : BuildFeatures, B : BuildType, C : DefaultConfig, D : ProductFlavor, E : AndroidResources, F : Installation> CommonExtension<A, B, C, D, E, F>.enableCompose() {
    buildFeatures {
        compose = true
    }
}

fun DefaultConfig.applyDefaultConfig(minSdkVersion: Int, targetSdkVersion: Int) {
    minSdk = minSdkVersion
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    (this as? ApplicationDefaultConfig)?.let { targetSdk = targetSdkVersion }
}
