plugins {
    `kotlin-dsl`
}

group = "com.e_commerce.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("convention.android.application") {
            id = "convention.android.application"
            implementationClass = "com.e_commerce.convention.AndroidApplicationConventionPlugin"
        }
        register("convention.android.library") {
            id = "convention.android.library"
            implementationClass = "com.e_commerce.convention.AndroidLibraryConventionPlugin"
        }
    }
}
