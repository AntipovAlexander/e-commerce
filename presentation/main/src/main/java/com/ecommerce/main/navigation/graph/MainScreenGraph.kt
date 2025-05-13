package com.ecommerce.main.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ecommerce.main.MainRoute
import com.ecommerce.main.MainScreen

fun NavGraphBuilder.mainScreenGraph() {
    navigation<MainScreenGraphRoute>(startDestination = MainRoute) {
        composable<MainRoute> {
            MainScreen()
        }
    }
}
