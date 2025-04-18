plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.e_commerce.product_details"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.kotlin.serialization.json)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)

    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}