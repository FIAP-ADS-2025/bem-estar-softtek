package br.com.fiap.bemestarsofttek.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.bemestarsofttek.screens.AssessmentScreen
import br.com.fiap.bemestarsofttek.screens.DashboardScreen
import br.com.fiap.bemestarsofttek.screens.DetailsScreen
import br.com.fiap.bemestarsofttek.screens.LoginScreen
import br.com.fiap.bemestarsofttek.screens.ResourcesScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Dashboard : Screen("dashboard")
    object Details : Screen("details")
    object Resources : Screen("resources")
    object Assessment : Screen("assessment")
}

@Composable
fun BemEstarNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        composable(Screen.Details.route) {
            DetailsScreen()
        }
        composable(Screen.Resources.route) {
            ResourcesScreen()
        }
        composable(Screen.Assessment.route) {
            AssessmentScreen(
                onComplete = { navController.navigate(Screen.Dashboard.route) },
                onCancel = { navController.navigate(Screen.Dashboard.route) }
            )
        }
    }
} 