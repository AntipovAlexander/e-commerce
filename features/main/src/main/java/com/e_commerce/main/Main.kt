package com.e_commerce.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.e_commerce.browse.BrowseScreen
import com.e_commerce.cart.CartScreen
import com.e_commerce.favourites.FavouritesScreen
import com.e_commerce.home.HomeScreen
import com.e_commerce.main.navigation.bottom.BottomNavigationItem
import com.e_commerce.main.navigation.bottom.mainBottomNavigation
import com.e_commerce.profile.ProfileScreen

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar { index -> resolveNavigation(index, navController) }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            graph = navController.mainBottomNavigation(),
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun BottomBar(onItemChanged: (Int) -> Unit) {
    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(containerColor = Color.White) {
        BottomNavigationItem.entries.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    onItemChanged(index)
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = {
                    Text(
                        item.title,
                        color = if (index == selectedNavigationIndex.intValue)
                            Color.Black
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

private fun resolveNavigation(index: Int, navController: NavHostController) {
    when (index) {
        0 -> navController.navigate(HomeScreen)
        1 -> navController.navigate(BrowseScreen)
        2 -> navController.navigate(FavouritesScreen)
        3 -> navController.navigate(CartScreen)
        4 -> navController.navigate(ProfileScreen)
        else -> throw IllegalArgumentException("Wrong bottom index: $index")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}