package com.e_commerce.main.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.e_commerce.main.MainScreen

fun NavGraphBuilder.mainScreenGraph() {
    navigation<MainScreenFlow>(startDestination = MainScreen) {
        composable<MainScreen> {
            MainScreen()
        }
    }
}

