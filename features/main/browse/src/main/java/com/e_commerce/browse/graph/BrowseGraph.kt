package com.e_commerce.browse.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.e_commerce.browse.BrowseRoute
import com.e_commerce.browse.BrowseScreen
import com.e_commerce.product_details.ProductDetailsRoute
import com.e_commerce.product_details.ProductDetailsScreen

@Composable
fun BrowseGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        graph = navController.createGraph(startDestination = BrowseRoute) {
            composable<BrowseRoute> {
                BrowseScreen { navController.navigate(ProductDetailsRoute("Browse")) }
            }
            composable<ProductDetailsRoute> { backStackEntry ->
                val route: ProductDetailsRoute = backStackEntry.toRoute()
                ProductDetailsScreen(route.cameFrom)
            }
        }
    )
}
