package com.e_commerce.cart.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.e_commerce.cart.CartRoute
import com.e_commerce.cart.CartScreen
import com.e_commerce.product_details.presentation.ProductDetailsRoute
import com.e_commerce.product_details.presentation.ProductDetailsScreen

@Composable
fun CartGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        graph = navController.createGraph(startDestination = CartRoute) {
            composable<CartRoute> {
                CartScreen { navController.navigate(ProductDetailsRoute("Cart")) }
            }
            composable<ProductDetailsRoute> { backStackEntry ->
                val route: ProductDetailsRoute = backStackEntry.toRoute()
                ProductDetailsScreen(route.cameFrom)
            }
        }
    )
}
