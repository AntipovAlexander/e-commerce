plugins {
    id("convention.android.feature")
}

android {
    namespace = "com.ecommerce.presentation.core"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}
