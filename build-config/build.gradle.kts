/**
 * This module serves as a centralized location for all BuildConfig fields across the application.
 *
 * Purpose:
 * - Prevents unnecessary BuildConfig generation in every module
 * - Provides a single source of truth for all configuration values
 * - Reduces build time by avoiding redundant BuildConfig generation
 * - Makes configuration management and updates easier
 *
 * Usage:
 * 1. Add configuration values here in buildConfigField
 * 2. Other modules should depend on this module instead of generating their own BuildConfig
 * 3. Access values via: com.ecommerce.build_config.BuildConfig
 *
 */

plugins {
    id("convention.android.library")
}

android {
    namespace = "com.ecommerce.build_config"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_BASE_URL", "\"dummyjson.com\"")
        }

        release {
            buildConfigField("String", "API_BASE_URL", "\"dummyjson.com\"")
        }
    }
}
