package com.e_commerce.favourites.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.e_commerce.favourites.FavouritesRoute
import com.e_commerce.favourites.FavouritesScreen
import com.e_commerce.product_details.ProductDetailsRoute
import com.e_commerce.product_details.ProductDetailsScreen

@Composable
fun FavouritesGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        graph = navController.createGraph(startDestination = FavouritesRoute) {
            composable<FavouritesRoute> {
                FavouritesScreen { navController.navigate(ProductDetailsRoute("Favourites")) }
            }
            composable<ProductDetailsRoute> { backStackEntry ->
                val route: ProductDetailsRoute = backStackEntry.toRoute()
                ProductDetailsScreen(route.cameFrom)
            }
        }
    )
}
