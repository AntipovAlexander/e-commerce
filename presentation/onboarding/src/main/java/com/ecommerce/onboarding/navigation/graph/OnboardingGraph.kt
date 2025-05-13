package com.ecommerce.onboarding.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ecommerce.core.navigation.slideIn
import com.ecommerce.core.navigation.slidingComposable
import com.ecommerce.forgot.ForgotPasswordRoute
import com.ecommerce.forgot.ForgotPasswordScreen
import com.ecommerce.onboarding.OnboardingScreen
import com.ecommerce.onboarding.navigation.OnboardingScreenRoute
import com.ecommerce.signin.SignInRoute
import com.ecommerce.signin.SignInScreen
import com.ecommerce.signup.SignUpRoute
import com.ecommerce.signup.SignUpScreen

fun NavGraphBuilder.onboardingGraph(navController: NavController, onUserLoggedIn: () -> Unit) {
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
                onSignUpClick = { navController.navigate(SignUpRoute) },
                onRestoreClick = { navController.navigate(ForgotPasswordRoute) },
                onLoggedIn = { onUserLoggedIn() }
            )
        }
        slidingComposable<SignUpRoute> {
            SignUpScreen(
                onSignUpClick = { navController.popBackStack() }
            )
        }
        composable<ForgotPasswordRoute> {
            ForgotPasswordScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
