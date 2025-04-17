plugins {
    id("convention.android.library")
}

android {
    namespace = "com.e_commerce.theme"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)
}