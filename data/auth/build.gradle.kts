plugins {
    id("convention.android.library")
    id("convention.dagger.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.ecommerce.data.auth"
}

dependencies {
    implementation(project(":data:core"))
    implementation(project(":domain:core"))
    implementation(project(":domain:auth"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.ktor)
}
