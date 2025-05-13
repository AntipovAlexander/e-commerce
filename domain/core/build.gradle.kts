plugins {
    id("convention.android.library")
    id("convention.dagger.hilt")
}

android {
    namespace = "com.ecommerce.domain.core"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}
