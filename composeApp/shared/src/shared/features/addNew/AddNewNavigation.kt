package shared.features.addNew

import androidx.compose.material3.Text
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import kotlinx.serialization.Serializable
import org.koin.core.annotation.KoinExperimentalAPI
import shared.components.AdaptiveAlertDialog
import shared.koinWorkaround.koinNavViewModel

@Serializable
internal object AddNew

@OptIn(KoinExperimentalAPI::class)
internal fun NavGraphBuilder.addNewScreen(
	onNavigateUp: () -> Unit,
) {
	dialog<AddNew>(dialogProperties = DialogProperties(usePlatformDefaultWidth = false)) {
		val viewModel = koinNavViewModel<AddNewViewModel>()

		AdaptiveAlertDialog(
			onDismissRequest = onNavigateUp,
			title = { Text("Legg til kjøtt") },
		) { contentPadding ->
			AddNewScreen(
				uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
				contentPadding = contentPadding,
				onNavigateUp = onNavigateUp,
				onUiEvent = viewModel::onUiEvent,
			)
		}
	}
}