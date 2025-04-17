plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.e_commerce.onboarding"
}

dependencies {
    implementation(project(":features:onboarding:sign_up"))
    implementation(project(":features:onboarding:sign_in"))
    implementation(project(":features:onboarding:forgot_password"))
    implementation(libs.androidx.navigation)
    implementation(libs.kotlin.serialization.json)
}