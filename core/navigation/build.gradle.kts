plugins {
    id("convention.android.library")
}

android {
    namespace = "com.e_commerce.core.navigation"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation)
}
