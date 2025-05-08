package com.ecommerce.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

fun Project.intVersion(name: String): Int {
    val catalogs = extensions
        .findByType(VersionCatalogsExtension::class.java)
        ?: error("Version catalogs not configured")

    val version = catalogs
        .named("libs")
        .findVersion(name)
        .orElseThrow { IllegalArgumentException("Version '$name' not found") }
        .requiredVersion

    return version.toInt()
}

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
