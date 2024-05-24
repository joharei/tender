package app.reitan.tender.addNew

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel

internal const val ADD_NEW_ROUTE = "add_new"

fun NavGraphBuilder.addNewScreen(
	onNavigateUp: () -> Unit,
	onNavigateToMap: () -> Unit,
) {
	composable(ADD_NEW_ROUTE) {
		val viewModel = koinViewModel<AddNewViewModel>()
		AddNewScreen(
			uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
			onNavigateUp = onNavigateUp,
			onNavigateToMap = onNavigateToMap,
			onSetStartDate = viewModel::setStartDate,
		)
	}
}