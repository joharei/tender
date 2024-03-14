package app.reitan.tender.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.datetime.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()

	Scaffold {
		Column(
			modifier = Modifier
				.padding(it)
				.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp),
		) {
			TextField(
				modifier = Modifier.fillMaxWidth(),
				value = uiState.lat,
				onValueChange = viewModel::setLat,
				label = { Text("Lat") },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
			)
			TextField(
				modifier = Modifier.fillMaxWidth(),
				value = uiState.lon,
				onValueChange = viewModel::setLon,
				label = { Text("Lon") },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
			)

			var datePickerOpen by remember { mutableStateOf(false) }
			val dateRangePickerState = rememberDateRangePickerState(
				initialSelectedStartDateMillis = uiState.startDate?.atStartOfDayIn(TimeZone.currentSystemDefault())
					?.toEpochMilliseconds(),
				initialSelectedEndDateMillis = uiState.endDate?.atStartOfDayIn(TimeZone.currentSystemDefault())
					?.toEpochMilliseconds(),
			)
			val confirmEnabled by remember {
				derivedStateOf { with(dateRangePickerState) { selectedStartDateMillis != null && selectedEndDateMillis != null } }
			}
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
								dateRangePickerState.selectedStartDateMillis?.let {
									viewModel.setStartDate(
										Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault()).date
									)
								}
								dateRangePickerState.selectedEndDateMillis?.let {
									viewModel.setEndDate(
										Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault()).date
									)
								}
							},
							enabled = confirmEnabled,
						) {
							Text("OK")
						}
					}

					DateRangePicker(
						state = dateRangePickerState,
					)
				}
			}
			TextButton(
				onClick = { datePickerOpen = true },
			) {
				Text(if (uiState.startDate != null && uiState.endDate != null) "${uiState.startDate} - ${uiState.endDate}" else "Select dates")
			}
			
			Button(
				onClick = viewModel::calculate,
			) {
				Text("Calculate")
			}

			AnimatedVisibility(uiState.calculated24HoursDegrees != null) {
				Text("Døgngrader: ${uiState.calculated24HoursDegrees}")
			}
		}
	}
}