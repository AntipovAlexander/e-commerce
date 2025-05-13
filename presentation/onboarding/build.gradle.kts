plugins {
    id("convention.android.feature")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.ecommerce.onboarding"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":presentation:onboarding:sign_up"))
    implementation(project(":presentation:onboarding:sign_in"))
    implementation(project(":presentation:onboarding:forgot_password"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.ui)
    implementation(libs.androidx.navigation)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}
