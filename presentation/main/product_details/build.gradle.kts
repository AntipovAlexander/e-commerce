plugins {
    id("convention.android.feature")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.ecommerce.details"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)

    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}
