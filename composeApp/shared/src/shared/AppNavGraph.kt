package shared

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import shared.features.addNew.ADD_NEW_ROUTE
import shared.features.addNew.addNewScreen
import shared.features.map.mapScreen
import shared.features.map.navigateToMapScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
	NavHost(navController = navController, startDestination = ADD_NEW_ROUTE) {
		addNewScreen(onNavigateUp = navController::navigateUp, onNavigateToMap = navController::navigateToMapScreen)
		mapScreen()
	}
}