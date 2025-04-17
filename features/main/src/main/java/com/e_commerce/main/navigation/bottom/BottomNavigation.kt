package com.e_commerce.main.navigation.bottom

import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.e_commerce.browse.BrowseScreen
import com.e_commerce.cart.CartScreen
import com.e_commerce.favourites.FavouritesScreen
import com.e_commerce.home.HomeScreen
import com.e_commerce.profile.ProfileScreen

fun NavController.mainBottomNavigation() = createGraph(startDestination = HomeScreen) {
    // todo: provide callbacks
    composable<HomeScreen> {
        HomeScreen {}
    }
    composable<BrowseScreen> {
        BrowseScreen { }
    }
    composable<FavouritesScreen> {
        FavouritesScreen { }
    }
    composable<CartScreen> {
        CartScreen { }
    }
    composable<ProfileScreen> {
        ProfileScreen { }
    }
}