plugins {
    id("convention.android.library")
}

android {
    namespace = "com.ecommerce.domain"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
