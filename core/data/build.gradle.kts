plugins {
    id("convention.android.library")
}

android {
    namespace = "com.ecommerce.core.domain"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.bundles.ktx)
}
