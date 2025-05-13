plugins {
    id("convention.android.library")
}

android {
    namespace = "com.ecommerce.domain.core"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
