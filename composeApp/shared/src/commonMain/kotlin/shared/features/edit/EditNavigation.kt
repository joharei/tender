package shared.features.edit

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.features.edit.EditDestination
import presentation.features.edit.EditViewModel
import shared.components.AdaptiveAlertDialog
import presentation.features.edit.models.EditMode
import tender.composeapp.shared.generated.resources.Res
import tender.composeapp.shared.generated.resources.edit_title_add
import tender.composeapp.shared.generated.resources.edit_title_edit

@OptIn(KoinExperimentalAPI::class)
internal fun NavGraphBuilder.editScreen(
	onNavigateUp: () -> Unit,
) {
	dialog<EditDestination>(dialogProperties = DialogProperties(usePlatformDefaultWidth = false)) {
		val viewModel = koinNavViewModel<EditViewModel>()

		val uiState by viewModel.uiState.collectAsStateWithLifecycle()
		AdaptiveAlertDialog(
			onDismissRequest = onNavigateUp,
			title = {
				Text(
					when (uiState.editMode) {
						EditMode.AddNew -> stringResource(Res.string.edit_title_add)
						EditMode.Edit -> stringResource(Res.string.edit_title_edit)
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