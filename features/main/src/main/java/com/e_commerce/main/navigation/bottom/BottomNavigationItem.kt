package com.e_commerce.main.navigation.bottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.e_commerce.browse.BrowseScreen
import com.e_commerce.cart.CartScreen
import com.e_commerce.favourites.FavouritesScreen
import com.e_commerce.home.HomeScreen
import com.e_commerce.profile.ProfileScreen

enum class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: Any
) {
    HOME(
        title = "Home",
        icon = Icons.Default.Home,
        route = HomeScreen
    ),
    BROWSE(
        title = "Browse",
        icon = Icons.Default.Search,
        route = BrowseScreen
    ),
    FAVOURITES(
        title = "Favourites",
        icon = Icons.Default.FavoriteBorder,
        route = FavouritesScreen
    ),
    CART(
        title = "Cart",
        icon = Icons.Default.ShoppingCart,
        route = CartScreen
    ),
    PROFILE(
        title = "Profile",
        icon = Icons.Default.Person,
        route = ProfileScreen
    ),
}