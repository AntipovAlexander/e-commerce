package com.e_commerce.main.navigation.bottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.e_commerce.browse.BrowseRoute
import com.e_commerce.cart.CartRoute
import com.e_commerce.favourites.FavouritesRoute
import com.e_commerce.home.HomeRoute
import com.e_commerce.profile.ProfileRoute

enum class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: Any
) {
    HOME(
        title = "Home",
        icon = Icons.Default.Home,
        route = HomeRoute
    ),
    BROWSE(
        title = "Browse",
        icon = Icons.Default.Search,
        route = BrowseRoute
    ),
    FAVOURITES(
        title = "Favourites",
        icon = Icons.Default.FavoriteBorder,
        route = FavouritesRoute
    ),
    CART(
        title = "Cart",
        icon = Icons.Default.ShoppingCart,
        route = CartRoute
    ),
    PROFILE(
        title = "Profile",
        icon = Icons.Default.Person,
        route = ProfileRoute
    ),
}