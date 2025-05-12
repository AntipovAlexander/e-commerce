package com.ecommerce.convention

import com.android.build.api.dsl.LibraryExtension
import com.ecommerce.convention.utils.enableCompose
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class FeatureLibraryConventionPlugin : LibraryConventionPlugin() {
    override fun apply(project: Project) {
        super.apply(project)
        project.run {
            extensions.configure<LibraryExtension> { enableCompose(pluginManager) }
        }
    }
}
