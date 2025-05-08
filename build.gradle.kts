import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt) apply true
}

dependencies {
    detektPlugins(libs.detekt.compose)
    detektPlugins(libs.detekt.formatting)
}

detekt {
    toolVersion = "1.23.7"
    source.setFrom(files(rootProject.projectDir))
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = false
    parallel = true
    ignoreFailures = false
    autoCorrect = true
}

tasks.withType<Detekt>().configureEach {
    autoCorrect = true
    reports {
        html.required.set(true)
        xml.required.set(false)
        sarif.required.set(false)
        md.required.set(false)
    }
    setExcludes(
        listOf(
            "**/resources/**",
            "**/build/**",
            "**/libs/**",
            "**/test/**",
            "**/rulesdetekt/**",
        )
    )
}
