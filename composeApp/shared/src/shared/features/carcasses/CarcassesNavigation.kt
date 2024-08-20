package shared.features.carcasses

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI

internal const val CARCASSES_ROUTE = "carcasses"

@OptIn(KoinExperimentalAPI::class)
internal fun NavGraphBuilder.carcassesScreen(
	onNavigateToAddNew: () -> Unit,
) {
	composable(CARCASSES_ROUTE) {
		val viewModel = koinNavViewModel<CarcassesViewModel>()
		CarcassesScreen(
			uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
			onAddNewClick = onNavigateToAddNew,
		)
	}
}