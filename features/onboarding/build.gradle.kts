plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.e_commerce.onboarding"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":features:onboarding:sign_up"))
    implementation(project(":features:onboarding:sign_in"))
    implementation(project(":features:onboarding:forgot_password"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.ui)
    implementation(libs.androidx.navigation)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}