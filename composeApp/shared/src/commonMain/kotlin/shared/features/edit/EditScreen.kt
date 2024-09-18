package shared.features.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import shared.features.edit.components.TimePickerDialog
import shared.features.edit.models.EditUiEvent
import shared.features.edit.models.EditUiState
import shared.ui.theme.AppTheme
import tender.composeapp.shared.generated.resources.Res
import tender.composeapp.shared.generated.resources.button_ok
import tender.composeapp.shared.generated.resources.button_save
import tender.composeapp.shared.generated.resources.edit_label_lat
import tender.composeapp.shared.generated.resources.edit_label_lon
import tender.composeapp.shared.generated.resources.edit_label_name
import tender.composeapp.shared.generated.resources.edit_label_start_date
import tender.composeapp.shared.generated.resources.edit_label_start_time

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
	uiState: EditUiState,
	contentPadding: PaddingValues = PaddingValues(),
	onNavigateUp: () -> Unit,
	onUiEvent: (EditUiEvent) -> Unit,
) {
	LaunchedEffect(uiState) {
		snapshotFlow { uiState.saveCompleted }
			.collect {
				if (it) {
					onNavigateUp()
				}
			}
	}

	Column(
		modifier = Modifier
			.padding(contentPadding)
			.padding(horizontal = 16.dp, vertical = 24.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp),
	) {
		OutlinedTextField(
			modifier = Modifier.fillMaxWidth(),
			value = uiState.name.orEmpty(),
			onValueChange = { onUiEvent(EditUiEvent.OnSetName(it)) },
			label = { Text(stringResource(Res.string.edit_label_name)) },
		)

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(16.dp),
		) {
			OutlinedTextField(
				modifier = Modifier.weight(1f),
				value = uiState.lat.orEmpty(),
				onValueChange = { onUiEvent(EditUiEvent.OnSetLat(it)) },
				label = { Text(stringResource(Res.string.edit_label_lat)) },
			)
			OutlinedTextField(
				modifier = Modifier.weight(1f),
				value = uiState.lon.orEmpty(),
				onValueChange = { onUiEvent(EditUiEvent.OnSetLon(it)) },
				label = { Text(stringResource(Res.string.edit_label_lon)) },
			)
		}

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(16.dp),
		) {
			var dateExpanded by remember { mutableStateOf(false) }
			val datePickerState = rememberDatePickerState()
			LaunchedEffect(datePickerState) {
				snapshotFlow { datePickerState.selectedDateMillis }
					.filterNotNull()
					.collect {
						onUiEvent(
							EditUiEvent.OnSetStartDate(
								Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault()).date,
							),
						)
					}
			}
			ExposedDropdownMenuBox(
				modifier = Modifier.weight(1f),
				expanded = dateExpanded,
				onExpandedChange = { dateExpanded = it },
			) {
				OutlinedTextField(
					modifier = Modifier
						.fillMaxWidth()
						.menuAnchor(MenuAnchorType.PrimaryNotEditable),
					value = uiState.startDate?.toString().orEmpty(),
					onValueChange = {},
					readOnly = true,
					singleLine = true,
					label = { Text(stringResource(Res.string.edit_label_start_date)) },
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dateExpanded) },
					colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
				)
			}
			if (dateExpanded) {
				DatePickerDialog(
					onDismissRequest = { dateExpanded = false },
					confirmButton = {
						TextButton(
							onClick = {
								dateExpanded = false
							},
						) { Text(stringResource(Res.string.button_ok)) }
					},
				) {
					DatePicker(
						state = datePickerState,
					)
				}
			}

			var timeExpanded by remember { mutableStateOf(false) }
			val timePickerState = rememberTimePickerState(
				initialHour = uiState.startTime?.hour ?: 0,
				initialMinute = uiState.startTime?.minute ?: 0,
			)
			ExposedDropdownMenuBox(
				modifier = Modifier.weight(1f),
				expanded = timeExpanded,
				onExpandedChange = { timeExpanded = it },
			) {
				OutlinedTextField(
					modifier = Modifier
						.fillMaxWidth()
						.menuAnchor(MenuAnchorType.PrimaryNotEditable),
					value = uiState.startTime?.toString().orEmpty(),
					onValueChange = {},
					readOnly = true,
					singleLine = true,
					label = { Text(stringResource(Res.string.edit_label_start_time)) },
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = timeExpanded) },
					colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
				)
			}
			if (timeExpanded) {
				TimePickerDialog(
					onCancel = { timeExpanded = false },
					onConfirm = {
						onUiEvent(EditUiEvent.OnSetStartTime(LocalTime(timePickerState.hour, timePickerState.minute)))
						timeExpanded = false
					},
				) {
					TimePicker(
						state = timePickerState,
					)
				}
			}
		}

		Button(
			modifier = Modifier.fillMaxWidth(),
			onClick = { onUiEvent(EditUiEvent.OnSave) },
			enabled = uiState.saveButtonEnabled,
		) {
			Text(stringResource(Res.string.button_save))
		}
	}
}

@Preview
@Composable
private fun PreviewAddNewScreen() {
	AppTheme {
		EditScreen(
			uiState = EditUiState(),
			onNavigateUp = {},
			onUiEvent = {},
		)
	}
}