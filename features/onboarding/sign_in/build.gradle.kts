plugins {
    id("convention.android.library")
    id("convention.dagger.hilt")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.ecommerce.signin"
}

dependencies {
    implementation(project(":core:ui"))
    detektPlugins(libs.detekt.compose)
    detektPlugins(libs.detekt.formatting)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)

    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}
