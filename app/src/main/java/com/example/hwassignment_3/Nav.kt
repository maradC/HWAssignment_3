package com.example.hwassignment_3

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Nav(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "workerlist", modifier = modifier) {
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