plugins {
    id("convention.android.application")
    id("convention.dagger.hilt")
}

android {
    namespace = "com.ecommerce.app"
}

dependencies {
    implementation(project(":presentation:core"))
    implementation(project(":data:core"))
    implementation(project(":domain:core"))
    implementation(project(":presentation:onboarding"))
    implementation(project(":presentation:onboarding:sign_up"))
    implementation(project(":presentation:onboarding:sign_in"))
    implementation(project(":presentation:onboarding:forgot_password"))
    implementation(project(":presentation:main"))
    implementation(project(":presentation:main:home"))
    implementation(project(":presentation:main:browse"))
    implementation(project(":presentation:main:favourites"))
    implementation(project(":presentation:main:cart"))
    implementation(project(":presentation:main:profile"))
    implementation(project(":presentation:main:product_details"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.ktx)
    implementation(libs.bundles.ui)
}
