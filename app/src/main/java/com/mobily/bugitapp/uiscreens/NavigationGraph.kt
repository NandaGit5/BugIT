package com.mobily.bugitapp.uiscreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


    @Composable
    fun NavAppGraph() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") { SplashScreenUI(navController)}
            composable("dashboardUI") { DashboardUI(navController) }
            composable("tasksList") { TaskListScreen(navController) }
            composable("addBug") { AddBugScreen(navController) }
        }
    }
//}