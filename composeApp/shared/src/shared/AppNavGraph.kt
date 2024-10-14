package shared

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import presentation.features.edit.EditDestination
import shared.features.edit.editScreen
import shared.features.carcasses.CarcassesDestination
import shared.features.carcasses.carcassesScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
	NavHost(navController = navController, startDestination = CarcassesDestination) {
		carcassesScreen(onNavigateToAddNew = { navController.navigate(EditDestination(carcassId = it)) })
		editScreen(onNavigateUp = navController::navigateUp)
	}
}