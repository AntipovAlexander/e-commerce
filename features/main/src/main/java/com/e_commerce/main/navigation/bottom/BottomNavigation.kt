package com.e_commerce.main.navigation.bottom

import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.e_commerce.browse.graph.BrowseGraph
import com.e_commerce.browse.graph.BrowseGraphRoute
import com.e_commerce.cart.graph.CartGraph
import com.e_commerce.cart.graph.CartGraphRoute
import com.e_commerce.favourites.graph.FavouritesGraph
import com.e_commerce.favourites.graph.FavouritesGraphRoute
import com.e_commerce.home.graph.HomeGraph
import com.e_commerce.home.graph.HomeGraphRoute
import com.e_commerce.profile.ProfileRoute
import com.e_commerce.profile.ProfileScreen

fun NavController.mainBottomNavigation() = createGraph(startDestination = HomeGraphRoute) {
    composable<HomeGraphRoute> {
        HomeGraph()
    }
    composable<BrowseGraphRoute> {
        BrowseGraph()
    }
    composable<FavouritesGraphRoute> {
        FavouritesGraph()
    }
    composable<CartGraphRoute> {
        CartGraph()
    }
    composable<ProfileRoute> {
        ProfileScreen { }
    }
}