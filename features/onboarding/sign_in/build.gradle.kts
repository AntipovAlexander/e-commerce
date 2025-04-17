plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.e_commerce.sign_in"
}

dependencies {
    implementation(project(":core:theme"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.kotlin.serialization.json)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)
}