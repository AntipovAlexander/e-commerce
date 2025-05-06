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
    implementation(project(":core:navigation"))
    implementation(project(":features:onboarding"))
    implementation(project(":features:onboarding:sign_up"))
    implementation(project(":features:onboarding:sign_in"))
    implementation(project(":features:onboarding:forgot_password"))
    implementation(project(":features:main"))
    implementation(project(":features:main:home"))
    implementation(project(":features:main:browse"))
    implementation(project(":features:main:favourites"))
    implementation(project(":features:main:cart"))
    implementation(project(":features:main:profile"))
    implementation(project(":features:main:product_details"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)

    detektPlugins(libs.detekt.compose)
}
