plugins {
    `kotlin-dsl`
}

group = "com.ecommerce.buildlogic"

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
            implementationClass = "com.ecommerce.convention.ApplicationConventionPlugin"
        }
        register("convention.android.library") {
            id = "convention.android.library"
            implementationClass = "com.ecommerce.convention.LibraryConventionPlugin"
        }
        register("convention.android.feature") {
            id = "convention.android.feature"
            implementationClass = "com.ecommerce.convention.FeatureLibraryConventionPlugin"
        }
        register("convention.dagger.hilt") {
            id = "convention.dagger.hilt"
            implementationClass = "com.ecommerce.convention.HiltConventionPlugin"
        }
    }
}
