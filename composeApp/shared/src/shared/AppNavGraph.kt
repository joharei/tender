package shared

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import shared.features.addNew.AddNew
import shared.features.addNew.addNewScreen
import shared.features.carcasses.Carcasses
import shared.features.carcasses.carcassesScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
	NavHost(navController = navController, startDestination = Carcasses) {
		carcassesScreen(onNavigateToAddNew = { navController.navigate(AddNew) })
		addNewScreen(onNavigateUp = navController::navigateUp)
	}
}