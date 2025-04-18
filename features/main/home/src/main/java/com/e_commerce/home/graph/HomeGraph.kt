package com.e_commerce.home.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.e_commerce.home.HomeRoute
import com.e_commerce.home.HomeScreen
import com.e_commerce.product_details.ProductDetailsRoute
import com.e_commerce.product_details.ProductDetailsScreen

@Composable
fun HomeGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        graph = navController.createGraph(startDestination = HomeRoute) {
            composable<HomeRoute> {
                HomeScreen { navController.navigate(ProductDetailsRoute("Home")) }
            }
            composable<ProductDetailsRoute> { backStackEntry ->
                val route: ProductDetailsRoute = backStackEntry.toRoute()
                ProductDetailsScreen(route.cameFrom)
            }
        }
    )
}
