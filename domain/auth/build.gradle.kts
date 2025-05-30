plugins {
    id("convention.android.library")
    id("convention.dagger.hilt")
}

android {
    namespace = "com.ecommerce.domain.auth"
}

dependencies {
    implementation(project(":domain:core"))
}
