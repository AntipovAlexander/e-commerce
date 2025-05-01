package com.e_commerce.onboarding.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.e_commerce.core.navigation.slideIn
import com.e_commerce.core.navigation.slidingComposable
import com.e_commerce.forgot_password.ForgotPasswordRoute
import com.e_commerce.forgot_password.ForgotPasswordScreen
import com.e_commerce.onboarding.OnboardingScreen
import com.e_commerce.onboarding.navigation.OnboardingScreenRoute
import com.e_commerce.sign_in.SignInRoute
import com.e_commerce.sign_in.SignInScreen
import com.e_commerce.sign_up.SignUpRoute
import com.e_commerce.sign_up.SignUpScreen

fun NavGraphBuilder.onboardingGraph(
    navController: NavController,
    onUserLoggedIn: () -> Unit,
) {
    navigation<OnboardingGraphRoute>(startDestination = OnboardingScreenRoute) {
        composable<OnboardingScreenRoute> {
            OnboardingScreen {
                navController.navigate(SignInRoute) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            }
        }
        slidingComposable<SignInRoute>(
            enterTransition = {
                if (navController.previousBackStackEntry == null) null
                else slideIn()
            }
        ) {
            SignInScreen(
                onSignUpClicked = { navController.navigate(SignUpRoute) },
                onRestoreClicked = { navController.navigate(ForgotPasswordRoute) },
                onLoggedIn = { onUserLoggedIn() }
            )
        }
        slidingComposable<SignUpRoute> {
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

