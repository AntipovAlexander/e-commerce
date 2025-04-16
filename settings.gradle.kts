pluginManagement {
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
include(":features:home")
include(":features:browse")
include(":features:favourites")
include(":features:cart")
include(":features:profile")
include(":features:product_details")
include(":core:theme")
include(":core:navigation")
include(":features:onboarding")
