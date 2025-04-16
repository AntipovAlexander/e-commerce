package com.antipov.e_commerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.antipov.forgot_password.ForgotPasswordScreen
import com.antipov.onboarding.navigation.Onboarding
import com.antipov.sign_in.SignInScreen
import com.antipov.sign_up.SignUpScreen
import com.antipov.theme.EcommerceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Onboarding,
                ) {
                    navigation<Onboarding>(startDestination = SignInScreen) {
                        composable<SignInScreen> {
                            SignInScreen(
                                onSignUpClicked = { navController.navigate(SignUpScreen) },
                                onRestoreClicked = { navController.navigate(ForgotPasswordScreen) }
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
            }
        }
    }
}