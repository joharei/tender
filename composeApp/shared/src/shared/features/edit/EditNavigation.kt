package shared.features.edit

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import shared.components.AdaptiveAlertDialog
import shared.features.edit.models.EditMode

@Serializable
internal data class Edit(val carcassId: Long? = null)

@OptIn(KoinExperimentalAPI::class)
internal fun NavGraphBuilder.editScreen(
	onNavigateUp: () -> Unit,
) {
	dialog<Edit>(dialogProperties = DialogProperties(usePlatformDefaultWidth = false)) {
		val viewModel = koinNavViewModel<EditViewModel>()

		val uiState by viewModel.uiState.collectAsStateWithLifecycle()
		AdaptiveAlertDialog(
			onDismissRequest = onNavigateUp,
			title = {
				Text(
					when (uiState.editMode) {
						EditMode.AddNew -> "Legg til kjøtt"
						EditMode.Edit -> "Endre kjøtt"
					},
				)
			},
		) { contentPadding ->
			EditScreen(
				uiState = uiState,
				contentPadding = contentPadding,
				onNavigateUp = onNavigateUp,
				onUiEvent = viewModel::onUiEvent,
			)
		}
	}
}