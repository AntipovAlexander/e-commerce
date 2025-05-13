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
include(":presentation:core")
include(":presentation:onboarding:sign_up")
include(":presentation:onboarding:forgot_password")
include(":presentation:onboarding:sign_in")
include(":presentation:main:home")
include(":presentation:main:browse")
include(":presentation:main:favourites")
include(":presentation:main:cart")
include(":presentation:main:profile")
include(":presentation:main:product_details")
include(":presentation:onboarding")
include(":domain")
include(":data")
include(":data:core")
include(":domain:core")
