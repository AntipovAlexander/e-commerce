plugins {
    id("convention.android.library")
}

android {
    namespace = "com.ecommerce.core.domain"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
