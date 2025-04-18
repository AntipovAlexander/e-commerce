package com.e_commerce.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.e_commerce.forgot_password.ForgotPasswordRoute
import com.e_commerce.forgot_password.ForgotPasswordScreen
import com.e_commerce.sign_in.SignInRoute
import com.e_commerce.sign_in.SignInScreen
import com.e_commerce.sign_up.SignUpRoute
import com.e_commerce.sign_up.SignUpScreen

fun NavGraphBuilder.onboardingGraph(
    navController: NavController,
    onUserLoggedIn: () -> Unit
) {
    navigation<OnboardingRoute>(startDestination = SignInRoute) {
        composable<SignInRoute> {
            SignInScreen(
                onSignUpClicked = { navController.navigate(SignUpRoute) },
                onRestoreClicked = { navController.navigate(ForgotPasswordRoute) },
                onLoggedIn = { onUserLoggedIn() }
            )
        }
        composable<SignUpRoute> {
            SignUpScreen(
                onSignInClicked = { navController.popBackStack() },
                onRestoreClicked = { navController.navigate(ForgotPasswordRoute) }
            )
        }
        composable<ForgotPasswordRoute> {
            ForgotPasswordScreen(
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
}

