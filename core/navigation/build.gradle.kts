plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.e_commerce.navigation"
}

dependencies {
    // todo: delete this module?
    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.navigation)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}