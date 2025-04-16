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
include(":features:home")
include(":features:browse")
include(":features:favourites")
include(":features:cart")
include(":features:profile")
include(":features:sign_up")
include(":features:product_details")
include(":core:theme")
include(":features:forgot_password")
include(":features:sign_in")
