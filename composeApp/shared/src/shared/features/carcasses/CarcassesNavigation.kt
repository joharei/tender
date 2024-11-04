package shared.features.carcasses

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.features.carcasses.CarcassesViewModel

@Serializable
internal object CarcassesDestination

@OptIn(KoinExperimentalAPI::class)
internal fun NavGraphBuilder.carcassesScreen(
	onNavigateToAddNew: (carcassId: Long?) -> Unit,
) {
	composable<CarcassesDestination> {
		val viewModel = koinNavViewModel<CarcassesViewModel>()
		CarcassesScreen(
			uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
			onAddNewClick = { onNavigateToAddNew(null) },
			onDeleteClick = viewModel::deleteCarcass,
			onEditClick = onNavigateToAddNew,
		)
	}
}