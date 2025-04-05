package com.example.hwassignment_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hwassignment_3.ui.theme.HWAssignment_3Theme

data class NavItem(
    val title: String,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector,
    val route: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HWAssignment_3Theme {
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
                                ) // End NavigationBarItem
                            } // End of forEachIndexed
                        } // End NavigationBar
                    }
                ) { paddingValues ->
                    Nav(
                        navController = navController,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}