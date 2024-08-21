package shared

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import shared.features.addNew.addNewScreen
import shared.features.addNew.navigateToAddNewScreen
import shared.features.carcasses.CARCASSES_ROUTE
import shared.features.carcasses.carcassesScreen
import shared.features.map.mapScreen
import shared.features.map.navigateToMapScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
	NavHost(navController = navController, startDestination = CARCASSES_ROUTE) {
		carcassesScreen(onNavigateToAddNew = navController::navigateToAddNewScreen)
		addNewScreen(onNavigateUp = navController::navigateUp)
		mapScreen()
	}
}