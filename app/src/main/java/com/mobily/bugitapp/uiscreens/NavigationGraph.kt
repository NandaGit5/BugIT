package com.mobily.bugitapp.uiscreens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobily.bugitapp.viewmodels.BugITViewModel


@Composable
    fun NavAppGraph() {
        val navController = rememberNavController()
        val bugReportViewModel : BugITViewModel = viewModel()

        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") { SplashScreenUI(navController)}
            composable("dashboardUI") { DashboardUI(navController) }
            composable("tasksList") { TaskListScreen(navController) }
            composable("addBug") { AddBugScreen(navController,viewModel = bugReportViewModel) }
        }
    }
//}