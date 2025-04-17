plugins {
    id("convention.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.e_commerce.navigation"
}

dependencies {

    implementation(libs.kotlin.serialization.json)
    implementation(libs.androidx.navigation)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}