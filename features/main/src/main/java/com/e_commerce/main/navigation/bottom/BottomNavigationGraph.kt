package com.e_commerce.main.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.e_commerce.browse.BrowseRoute
import com.e_commerce.browse.BrowseScreen
import com.e_commerce.cart.CartRoute
import com.e_commerce.cart.CartScreen
import com.e_commerce.favourites.FavouritesRoute
import com.e_commerce.favourites.FavouritesScreen
import com.e_commerce.home.HomeRoute
import com.e_commerce.home.HomeScreen
import com.e_commerce.main.navigation.bottom.routes.BrowseGraphRoute
import com.e_commerce.main.navigation.bottom.routes.CartGraphRoute
import com.e_commerce.main.navigation.bottom.routes.FavouritesGraphRoute
import com.e_commerce.main.navigation.bottom.routes.HomeGraphRoute
import com.e_commerce.product_details.presentation.ProductDetailsRoute
import com.e_commerce.product_details.presentation.ProductDetailsScreen
import com.e_commerce.profile.ProfileRoute
import com.e_commerce.profile.ProfileScreen

fun NavController.bottomNavigationGraph() = createGraph(startDestination = HomeGraphRoute) {
    composable<HomeGraphRoute> {
        Graph { it.homeGraph() }
    }
    composable<BrowseGraphRoute> {
        Graph { it.browserGraph() }
    }
    composable<FavouritesGraphRoute> {
        Graph { it.favouritesGraph() }
    }
    composable<CartGraphRoute> {
        Graph { it.cartGraph() }
    }
    composable<ProfileRoute> {
        ProfileScreen { }
    }
}

@Composable
private fun Graph(builder: (NavController) -> NavGraph) {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        graph = builder(controller)
    )
}

private fun NavController.homeGraph() = createGraph(startDestination = HomeRoute) {
    composable<HomeRoute> {
        HomeScreen { navigate(ProductDetailsRoute("Home")) }
    }
    composable<ProductDetailsRoute> { backStackEntry ->
        val route: ProductDetailsRoute = backStackEntry.toRoute()
        ProductDetailsScreen(route.cameFrom)
    }
}

private fun NavController.browserGraph() = createGraph(startDestination = BrowseRoute) {
    composable<BrowseRoute> { entry ->
        BrowseScreen(
            onOpenDetailsClick = { navigate(ProductDetailsRoute("Browse")) }
        )
    }
    composable<ProductDetailsRoute> { backStackEntry ->
        val route: ProductDetailsRoute = backStackEntry.toRoute()
        ProductDetailsScreen(route.cameFrom)
    }
}

private fun NavController.favouritesGraph() = createGraph(startDestination = FavouritesRoute) {
    composable<FavouritesRoute> {
        FavouritesScreen { navigate(ProductDetailsRoute("Favourites")) }
    }
    composable<ProductDetailsRoute> { backStackEntry ->
        val route: ProductDetailsRoute = backStackEntry.toRoute()
        ProductDetailsScreen(route.cameFrom)
    }
}

private fun NavController.cartGraph() = createGraph(startDestination = CartRoute) {
    composable<CartRoute> {
        CartScreen { navigate(ProductDetailsRoute("Cart")) }
    }
    composable<ProductDetailsRoute> { backStackEntry ->
        val route: ProductDetailsRoute = backStackEntry.toRoute()
        ProductDetailsScreen(route.cameFrom)
    }
}
