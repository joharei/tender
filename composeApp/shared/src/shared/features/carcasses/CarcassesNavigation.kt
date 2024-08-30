package shared.features.carcasses

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.core.annotation.KoinExperimentalAPI
import shared.koinWorkaround.koinNavViewModel

@Serializable
internal object Carcasses

@OptIn(KoinExperimentalAPI::class)
internal fun NavGraphBuilder.carcassesScreen(
	onNavigateToAddNew: () -> Unit,
) {
	composable<Carcasses> {
		val viewModel = koinNavViewModel<CarcassesViewModel>()
		CarcassesScreen(
			uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
			onAddNewClick = onNavigateToAddNew,
		)
	}
}