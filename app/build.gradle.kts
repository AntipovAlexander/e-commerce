import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("convention.android.application")
    alias(libs.plugins.detekt)
}

detekt {
    toolVersion = "1.23.7"
    source.setFrom(files(rootProject.projectDir))
    config.setFrom(file("../config/detekt/detekt.yml"))
    buildUponDefaultConfig = false
    parallel = true
    autoCorrect = true
    ignoreFailures = false
}

tasks.withType<Detekt>().configureEach {
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
            "youtube_player/**"
        )
    )
}

android {
    namespace = "com.ecommerce.app"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":features:onboarding"))
    implementation(project(":features:main"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)

    detektPlugins(libs.detekt.compose)
}
