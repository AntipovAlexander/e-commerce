package com.e_commerce.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.e_commerce.main.navigation.graph.MainScreenGraphRoute
import com.e_commerce.main.navigation.graph.mainScreenGraph
import com.e_commerce.onboarding.navigation.OnboardingRoute
import com.e_commerce.onboarding.navigation.onboardingGraph

@Composable
fun MainActivity.MainNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = OnboardingRoute,
    ) {
        onboardingGraph(
            navController = navController,
            onUserLoggedIn = { switchToMainGraph(navController) }
        )
        mainScreenGraph()
    }
}

private fun NavGraphBuilder.switchToMainGraph(navController: NavHostController) {
    navController.navigate(MainScreenGraphRoute) {
        popUpTo<OnboardingRoute> { inclusive = true }
    }
}