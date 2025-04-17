package com.antipov.e_commerce

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.antipov.main.navigation.MainScreenFlow
import com.antipov.main.navigation.mainScreenGraph
import com.antipov.onboarding.navigation.OnboardingFlow
import com.antipov.onboarding.navigation.onboardingGraph

@Composable
fun MainActivity.MainNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = OnboardingFlow,
    ) {
        onboardingGraph(
            navController = navController,
            onUserLoggedIn = { switchToMainGraph(navController) }
        )
        mainScreenGraph()
    }
}

private fun NavGraphBuilder.switchToMainGraph(navController: NavHostController) {
    navController.navigate(MainScreenFlow) {
        popUpTo<OnboardingFlow> { inclusive = true }
    }
}