package app.reitan.tender

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import app.reitan.tender.addNew.ADD_NEW_ROUTE
import app.reitan.tender.addNew.addNewScreen
import app.reitan.tender.map.mapScreen
import app.reitan.tender.map.navigateToMapScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
	NavHost(navController = navController, startDestination = ADD_NEW_ROUTE) {
		addNewScreen(onNavigateUp = navController::navigateUp, onNavigateToMap = navController::navigateToMapScreen)
		mapScreen()
	}
}