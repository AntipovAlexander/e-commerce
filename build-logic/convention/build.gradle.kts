plugins {
    `kotlin-dsl`
}

group = "com.antipov.buildlogic"

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
            implementationClass = "com.antipov.convention.AndroidApplicationConventionPlugin"
        }
        register("convention.android.library") {
            id = "convention.android.library"
            implementationClass = "com.antipov.convention.AndroidLibraryConventionPlugin"
        }
    }
}