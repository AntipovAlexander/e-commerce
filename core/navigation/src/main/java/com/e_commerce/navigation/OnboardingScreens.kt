package com.e_commerce.navigation

import kotlinx.serialization.Serializable

sealed class OnboardingScreens : Navigation {
    @Serializable
    data object ForgotPassword : OnboardingScreens()

    @Serializable
    data object SignIn : OnboardingScreens()

    @Serializable
    data object SignUp : OnboardingScreens()
}