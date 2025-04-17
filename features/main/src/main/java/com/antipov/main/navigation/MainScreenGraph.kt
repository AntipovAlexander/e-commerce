package com.antipov.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.antipov.main.MainScreen

fun NavGraphBuilder.mainScreenGraph() {
    navigation<MainScreenFlow>(startDestination = MainScreen) {
        composable<MainScreen> {
            MainScreen()
        }
    }
}

