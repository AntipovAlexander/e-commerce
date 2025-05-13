plugins {
    id("convention.android.library")
}

android {
    namespace = "com.ecommerce.data.core"
}

dependencies {
    implementation(project(":domain:core"))
    implementation(libs.androidx.core.ktx)
}
