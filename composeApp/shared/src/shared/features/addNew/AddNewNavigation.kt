package shared.features.addNew

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI

private const val ADD_NEW_ROUTE = "add_new"

@OptIn(KoinExperimentalAPI::class)
internal fun NavGraphBuilder.addNewScreen(
	onNavigateUp: () -> Unit,
	onNavigateToMap: () -> Unit,
) {
	composable(ADD_NEW_ROUTE) {
		val viewModel = koinNavViewModel<AddNewViewModel>()
		AddNewScreen(
			uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
			onNavigateUp = onNavigateUp,
			onNavigateToMap = onNavigateToMap,
			onSetStartDate = viewModel::setStartDate,
		)
	}
}

internal fun NavController.navigateToAddNewScreen() = navigate(ADD_NEW_ROUTE)