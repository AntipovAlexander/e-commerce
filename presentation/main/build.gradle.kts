plugins {
    id("convention.android.feature")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.ecommerce.main"
}

dependencies {
    implementation(project(":presentation:core"))
    implementation(project(":presentation:main:home"))
    implementation(project(":presentation:main:browse"))
    implementation(project(":presentation:main:favourites"))
    implementation(project(":presentation:main:cart"))
    implementation(project(":presentation:main:profile"))
    implementation(project(":presentation:main:product_details"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.navigation)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)

    implementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.tooling.preview)
}
