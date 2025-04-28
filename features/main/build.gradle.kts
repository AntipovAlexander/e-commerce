plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.e_commerce.main"
}

dependencies {
    implementation(project(":core:theme"))
    implementation(project(":features:main:home"))
    implementation(project(":features:main:browse"))
    implementation(project(":features:main:favourites"))
    implementation(project(":features:main:cart"))
    implementation(project(":features:main:profile"))
    implementation(project(":features:main:product_details"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.navigation)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)

    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}