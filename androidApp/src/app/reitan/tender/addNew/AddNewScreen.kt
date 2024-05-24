package app.reitan.tender.addNew

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.reitan.tender.addNew.models.MainUiState
import kotlinx.datetime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewScreen(
	uiState: MainUiState,
	onNavigateUp: () -> Unit,
	onNavigateToMap: () -> Unit,
	onSetStartDate: (LocalDate) -> Unit,
	onCalculate: () -> Unit,
) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text("Legg til kjøtt") },
				navigationIcon = {
					IconButton(onClick = onNavigateUp) {
						Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
					}
				}
			)
		},
	) { contentPadding ->
		Column(
			modifier = Modifier
				.padding(contentPadding)
				.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp),
		) {
			ListItem(
				modifier = Modifier.clickable(onClick = onNavigateToMap),
				headlineContent = { Text("Plassering") },
				supportingContent = {
					Text(uiState.location?.let { (lat, lon) -> "${"%,.6f".format(lat)}, ${"%,.6f".format(lon)}" }
						?: "Velg plassering")
				},
				trailingContent = { Icon(Icons.AutoMirrored.Default.ArrowRight, contentDescription = null) },
			)

			var datePickerOpen by remember { mutableStateOf(false) }
			val datePickerState = rememberDatePickerState(
				initialSelectedDateMillis = uiState.startDate?.atStartOfDayIn(TimeZone.currentSystemDefault())
					?.toEpochMilliseconds(),
			)
			val confirmEnabled = datePickerState.selectedDateMillis != null
			if (datePickerOpen) {
				ModalBottomSheet(
					onDismissRequest = { datePickerOpen = false },
					sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
				) {
					Row(
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 12.dp),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceBetween,
					) {
						IconButton(onClick = { datePickerOpen = false }) {
							Icon(Icons.Filled.Close, contentDescription = "Cancel")
						}
						TextButton(
							onClick = {
								datePickerOpen = false
								datePickerState.selectedDateMillis?.let {
									onSetStartDate(
										Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault()).date
									)
								}
							},
							enabled = confirmEnabled,
						) {
							Text("OK")
						}
					}

					DatePicker(
						state = datePickerState,
					)
				}
			}
			TextButton(
				onClick = { datePickerOpen = true },
			) {
				Text(if (uiState.startDate != null) uiState.startDate.toString() else "Select dates")
			}

			Button(
				onClick = onCalculate,
			) {
				Text("Calculate")
			}

			AnimatedVisibility(uiState.calculated24HoursDegrees != null) {
				Text("Døgngrader: ${uiState.calculated24HoursDegrees}")
			}
		}
	}
}