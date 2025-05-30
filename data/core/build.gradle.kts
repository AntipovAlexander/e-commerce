plugins {
    id("convention.android.library")
    id("convention.dagger.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.ecommerce.data.core"
}

dependencies {
    implementation(project(":domain:core"))
    implementation(project(":build-config"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.ktor)
}
