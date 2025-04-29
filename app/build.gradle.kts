plugins {
    id("convention.android.application")
}

android {
    namespace = "com.e_commerce.app"
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
}