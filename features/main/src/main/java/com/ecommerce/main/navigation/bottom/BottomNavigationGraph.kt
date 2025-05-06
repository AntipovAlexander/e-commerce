package com.ecommerce.main.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.ecommerce.browse.BrowseRoute
import com.ecommerce.browse.BrowseScreen
import com.ecommerce.cart.CartRoute
import com.ecommerce.cart.CartScreen
import com.ecommerce.details.presentation.ProductDetailsRoute
import com.ecommerce.details.presentation.ProductDetailsScreen
import com.ecommerce.favourites.FavouritesRoute
import com.ecommerce.favourites.FavouritesScreen
import com.ecommerce.home.HomeRoute
import com.ecommerce.home.HomeScreen
import com.ecommerce.main.navigation.bottom.routes.BrowseGraphRoute
import com.ecommerce.main.navigation.bottom.routes.CartGraphRoute
import com.ecommerce.main.navigation.bottom.routes.FavouritesGraphRoute
import com.ecommerce.main.navigation.bottom.routes.HomeGraphRoute
import com.ecommerce.profile.ProfileRoute
import com.ecommerce.profile.ProfileScreen

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
        ProfileScreen(onOpenDetailsClick = {})
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
        HomeScreen(onOpenDetailsClick = { navigate(ProductDetailsRoute("Home")) })
    }
    composable<ProductDetailsRoute> { backStackEntry ->
        val route: ProductDetailsRoute = backStackEntry.toRoute()
        ProductDetailsScreen(text = route.cameFrom, viewModel = viewModel())
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
        ProductDetailsScreen(text = route.cameFrom, viewModel = viewModel())
    }
}

private fun NavController.favouritesGraph() = createGraph(startDestination = FavouritesRoute) {
    composable<FavouritesRoute> {
        FavouritesScreen(onOpenDetailsClick = { navigate(ProductDetailsRoute("Favourites")) })
    }
    composable<ProductDetailsRoute> { backStackEntry ->
        val route: ProductDetailsRoute = backStackEntry.toRoute()
        ProductDetailsScreen(text = route.cameFrom, viewModel = viewModel())
    }
}

private fun NavController.cartGraph() = createGraph(startDestination = CartRoute) {
    composable<CartRoute> {
        CartScreen(onOpenDetailsClick = { navigate(ProductDetailsRoute("Cart")) })
    }
    composable<ProductDetailsRoute> { backStackEntry ->
        val route: ProductDetailsRoute = backStackEntry.toRoute()
        ProductDetailsScreen(text = route.cameFrom, viewModel = viewModel())
    }
}
