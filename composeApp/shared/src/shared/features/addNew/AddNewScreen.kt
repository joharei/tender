package shared.features.addNew

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import shared.features.addNew.components.TimePickerDialog
import shared.features.addNew.models.AddNewUiEvent
import shared.features.addNew.models.AddNewUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewScreen(
	uiState: AddNewUiState,
	contentPadding: PaddingValues = PaddingValues(),
	onNavigateUp: () -> Unit,
	onUiEvent: (AddNewUiEvent) -> Unit,
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
			onValueChange = { onUiEvent(AddNewUiEvent.OnSetName(it)) },
			label = { Text("Navn") },
		)

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(16.dp),
		) {
			OutlinedTextField(
				modifier = Modifier.weight(1f),
				value = uiState.lat.orEmpty(),
				onValueChange = { onUiEvent(AddNewUiEvent.OnSetLat(it)) },
				label = { Text("Latitude") },
			)
			OutlinedTextField(
				modifier = Modifier.weight(1f),
				value = uiState.lon.orEmpty(),
				onValueChange = { onUiEvent(AddNewUiEvent.OnSetLon(it)) },
				label = { Text("Longitude") },
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
							AddNewUiEvent.OnSetStartDate(
								Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault()).date
							)
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
					label = { Text("Startdato") },
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dateExpanded) },
					colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
				)
			}
			if (dateExpanded) {
				DatePickerDialog(
					onDismissRequest = { dateExpanded = false },
					confirmButton = { TextButton(onClick = { dateExpanded = false }) { Text("Ok") } },
				) {
					DatePicker(
						state = datePickerState,
					)
				}
			}

			var timeExpanded by remember { mutableStateOf(false) }
			val timePickerState = rememberTimePickerState()
			val localTime by rememberUpdatedState(LocalTime(timePickerState.hour, timePickerState.minute))
			LaunchedEffect(timePickerState) {
				snapshotFlow { localTime }
					.collect {
						onUiEvent(AddNewUiEvent.OnSetStartTime(it))
					}
			}
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
					label = { Text("Starttid") },
					trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = timeExpanded) },
					colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
				)
			}
			if (timeExpanded) {
				TimePickerDialog(
					onCancel = { timeExpanded = false },
					onConfirm = { timeExpanded = false },
					title = "Velg tid",
				) {
					TimePicker(
						state = timePickerState,
					)
				}
			}
		}

		Button(
			modifier = Modifier.fillMaxWidth(),
			onClick = { onUiEvent(AddNewUiEvent.OnSave) },
			enabled = uiState.saveButtonEnabled,
		) {
			Text("Lagre")
		}
	}
}

@Preview
@Composable
private fun PreviewAddNewScreen() {
	MaterialTheme {
		AddNewScreen(
			uiState = AddNewUiState(),
			onNavigateUp = {},
			onUiEvent = {},
		)
	}
}