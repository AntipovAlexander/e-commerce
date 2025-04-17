plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.antipov.onboarding"
}

dependencies {
    implementation(project(":features:onboarding:sign_up"))
    implementation(project(":features:onboarding:sign_in"))
    implementation(project(":features:onboarding:forgot_password"))
    implementation(libs.androidx.navigation)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}