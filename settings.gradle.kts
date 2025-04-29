pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "e-commerce"
include(":app")
include(":features:onboarding:sign_up")
include(":features:onboarding:forgot_password")
include(":features:onboarding:sign_in")
include(":features:main:home")
include(":features:main:browse")
include(":features:main:favourites")
include(":features:main:cart")
include(":features:main:profile")
include(":features:main:product_details")
include(":features:onboarding")
include(":core:ui")
