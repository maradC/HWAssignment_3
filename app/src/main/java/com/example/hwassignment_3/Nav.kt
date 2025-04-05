package com.example.hwassignment_3

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

data class NavItem(
    val title: String,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector,
    val route: String
)

@Composable
fun Nav(modifier: Modifier = Modifier) {
    val navItems = listOf(
        NavItem(
            title = "Workers List",
            iconSelected = Icons.Filled.Home,
            iconUnselected = Icons.Outlined.Home,
            route = "workerlist"
        ),
        NavItem(
            title = "App info",
            iconSelected = Icons.Filled.Info,
            iconUnselected = Icons.Outlined.Info,
            route = "appinfo"
        )
    )

    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex)
                                    item.iconSelected
                                else item.iconUnselected,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(item.title) }
                    )//End Navigation Bar
                }//End of For Each Index
            } //End Navigation Bar
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "workerlist",
            modifier = modifier.padding(paddingValues)
        ) {
            composable(route = "workerlist") {
                WorkerListUI(
                    onWorkerSelected = { workerName ->
                        navController.navigate("details/$workerName")
                    },
                    modifier = modifier
                )
            }
            composable(route = "details/{workerName}") { backStackEntry ->
                val workerName = backStackEntry.arguments?.getString("workerName") ?: ""
                DetailsUI(
                    workerName = workerName,
                    onBackPressed = {
                        navController.popBackStack()
                    },
                    modifier = modifier
                )
            }
            composable(route = "appinfo") {
                AppInfoUI(
                    modifier = modifier
                )
            }
        }
    }
}