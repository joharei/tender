package shared

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import shared.features.edit.Edit
import shared.features.edit.editScreen
import shared.features.carcasses.Carcasses
import shared.features.carcasses.carcassesScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
	NavHost(navController = navController, startDestination = Carcasses) {
		carcassesScreen(onNavigateToAddNew = { navController.navigate(Edit(carcassId = it)) })
		editScreen(onNavigateUp = navController::navigateUp)
	}
}