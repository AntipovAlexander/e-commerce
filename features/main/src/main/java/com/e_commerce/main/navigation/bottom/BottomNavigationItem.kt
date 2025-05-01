package com.e_commerce.main.navigation.bottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.e_commerce.main.navigation.bottom.routes.BrowseGraphRoute
import com.e_commerce.main.navigation.bottom.routes.CartGraphRoute
import com.e_commerce.main.navigation.bottom.routes.FavouritesGraphRoute
import com.e_commerce.main.navigation.bottom.routes.HomeGraphRoute
import com.e_commerce.profile.ProfileRoute

enum class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: Any
) {
    HOME(
        title = "Home",
        icon = Icons.Default.Home,
        route = HomeGraphRoute
    ),
    BROWSE(
        title = "Browse",
        icon = Icons.Default.Search,
        route = BrowseGraphRoute
    ),
    FAVOURITES(
        title = "Favourites",
        icon = Icons.Default.FavoriteBorder,
        route = FavouritesGraphRoute
    ),
    CART(
        title = "Cart",
        icon = Icons.Default.ShoppingCart,
        route = CartGraphRoute
    ),
    PROFILE(
        title = "Profile",
        icon = Icons.Default.Person,
        route = ProfileRoute
    )
}
