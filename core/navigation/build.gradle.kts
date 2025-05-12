plugins {
    id("convention.android.feature")
}

android {
    namespace = "com.ecommerce.core.navigation"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation)
}
