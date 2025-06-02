package com.ecommerce.onboarding.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ecommerce.forgot.ForgotPasswordRoute
import com.ecommerce.forgot.ForgotPasswordScreen
import com.ecommerce.onboarding.OnboardingScreen
import com.ecommerce.onboarding.navigation.OnboardingScreenRoute
import com.ecommerce.presentation.core.navigation.slideIn
import com.ecommerce.presentation.core.navigation.slidingComposable
import com.ecommerce.signin.SignInRoute
import com.ecommerce.signin.SignInScreen
import com.ecommerce.signup.SignUpRoute
import com.ecommerce.signup.SignUpScreen

fun NavGraphBuilder.onboardingGraph(navController: NavController, onSignInSuccess: () -> Unit) {
    navigation<OnboardingGraphRoute>(startDestination = OnboardingScreenRoute) {
        composable<OnboardingScreenRoute> {
            OnboardingScreen(
                onProceedToAuthClick = {
                    navController.navigate(SignInRoute) {
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                    }
                }
            )
        }
        slidingComposable<SignInRoute>(
            enterTransition = {
                if (navController.previousBackStackEntry == null) {
                    null
                } else {
                    slideIn()
                }
            }
        ) {
            SignInScreen(
                onSignInSuccess = { onSignInSuccess() },
                onRestoreClick = { navController.navigate(ForgotPasswordRoute) },
                onSignUpClick = { navController.navigate(SignUpRoute) }
            )
        }
        slidingComposable<SignUpRoute> {
            SignUpScreen(
                onDismiss = { navController.popBackStack() }
            )
        }
        slidingComposable<ForgotPasswordRoute> {
            ForgotPasswordScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
