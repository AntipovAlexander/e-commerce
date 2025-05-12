plugins {
    id("convention.android.feature")
    id("convention.dagger.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.ecommerce.signin"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)
    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}
