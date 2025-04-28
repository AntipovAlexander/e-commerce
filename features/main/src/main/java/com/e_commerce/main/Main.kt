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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.e_commerce.core.theme.Theme
import com.e_commerce.main.navigation.bottom.BottomNavigationItem
import com.e_commerce.main.navigation.bottom.bottomNavigationGraph

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        containerColor = Theme.colors.backgroundPrimary,
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            graph = navController.bottomNavigationGraph(),
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    NavigationBar(containerColor = Color.White) {
        BottomNavigationItem.entries.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = navBackStackEntry.isSelected(item.route),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = {
                    Text(
                        item.title,
                        color = if (navBackStackEntry.isSelected(item.route))
                            Color.Black
                        else
                            Color.Gray
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

private fun NavBackStackEntry?.isSelected(route: Any) =
    this?.destination?.route == route::class.java.name

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}