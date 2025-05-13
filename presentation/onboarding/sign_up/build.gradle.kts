plugins {
    id("convention.android.feature")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.ecommerce.signup"
}

dependencies {
    implementation(project(":presentation:core"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.kotlin.serialization.json)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)

    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}
