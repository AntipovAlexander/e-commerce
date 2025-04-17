package com.e_commerce.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.e_commerce.forgot_password.ForgotPasswordScreen
import com.e_commerce.sign_in.SignInScreen
import com.e_commerce.sign_up.SignUpScreen

fun NavGraphBuilder.onboardingGraph(
    navController: NavController,
    onUserLoggedIn: () -> Unit
) {
    navigation<OnboardingFlow>(startDestination = SignInScreen) {
        composable<SignInScreen> {
            SignInScreen(
                onSignUpClicked = { navController.navigate(SignUpScreen) },
                onRestoreClicked = { navController.navigate(ForgotPasswordScreen) },
                onLoggedIn = { onUserLoggedIn() }
            )
        }
        composable<SignUpScreen> {
            SignUpScreen(
                onSignInClicked = { navController.popBackStack() },
                onRestoreClicked = { navController.navigate(ForgotPasswordScreen) }
            )
        }
        composable<ForgotPasswordScreen> {
            ForgotPasswordScreen(
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
}

