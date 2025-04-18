package com.e_commerce.main.navigation.bottom

import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.e_commerce.browse.BrowseRoute
import com.e_commerce.browse.BrowseScreen
import com.e_commerce.cart.CartRoute
import com.e_commerce.cart.CartScreen
import com.e_commerce.favourites.FavouritesRoute
import com.e_commerce.favourites.FavouritesScreen
import com.e_commerce.home.HomeRoute
import com.e_commerce.home.HomeScreen
import com.e_commerce.profile.ProfileRoute
import com.e_commerce.profile.ProfileScreen

fun NavController.mainBottomNavigation() = createGraph(startDestination = HomeRoute) {
    // todo: provide callbacks
    composable<HomeRoute> {
        HomeScreen {}
    }
    composable<BrowseRoute> {
        BrowseScreen { }
    }
    composable<FavouritesRoute> {
        FavouritesScreen { }
    }
    composable<CartRoute> {
        CartScreen { }
    }
    composable<ProfileRoute> {
        ProfileScreen { }
    }
}