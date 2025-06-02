package com.ecommerce.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ecommerce.main.navigation.graph.MainScreenGraphRoute
import com.ecommerce.main.navigation.graph.mainScreenGraph
import com.ecommerce.onboarding.navigation.graph.OnboardingGraphRoute
import com.ecommerce.onboarding.navigation.graph.onboardingGraph

@Composable
fun MainActivity.MainNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = OnboardingGraphRoute
    ) {
        onboardingGraph(
            navController = navController,
            onSignInSuccess = { switchToMainGraph(navController) }
        )
        mainScreenGraph()
    }
}

private fun NavGraphBuilder.switchToMainGraph(navController: NavHostController) {
    navController.navigate(MainScreenGraphRoute) {
        popUpTo<OnboardingGraphRoute> { inclusive = true }
    }
}
