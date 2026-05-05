package shared.features.edit

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.features.edit.EditDestination
import presentation.features.edit.EditViewModel
import presentation.features.edit.models.EditMode
import resources.MR
import shared.components.AdaptiveAlertDialog

internal fun NavGraphBuilder.editScreen(
	onNavigateUp: () -> Unit,
) {
	dialog<EditDestination>(dialogProperties = DialogProperties(usePlatformDefaultWidth = false)) {
		val viewModel = koinViewModel<EditViewModel>()

		val uiState by viewModel.uiState.collectAsStateWithLifecycle()
		AdaptiveAlertDialog(
			onDismissRequest = onNavigateUp,
			title = {
				Text(
					when (uiState.editMode) {
						EditMode.AddNew -> stringResource(MR.strings.edit_title_add)
						EditMode.Edit -> stringResource(MR.strings.edit_title_edit)
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
