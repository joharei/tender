package shared.features.carcasses.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.features.carcasses.models.CarcassUiState
import presentation.utils.format
import resources.MR
import shared.features.edit.components.TimePickerDialog
import shared.ui.theme.AppTheme
import kotlin.math.roundToInt
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carcass(
	modifier: Modifier = Modifier,
	state: CarcassUiState,
	onDeleteClick: () -> Unit,
	onEditClick: () -> Unit,
	onDoneClick: (doneDate: Instant) -> Unit,
) {
	Card(
		modifier = modifier,
		onClick = onEditClick,
	) {
		Column {
			Column(
				modifier = Modifier.padding(16.dp),
			) {
				Text(
					text = state.name,
					style = MaterialTheme.typography.headlineMedium,
				)

				ProvideTextStyle(MaterialTheme.typography.titleSmall) {
					Text(
						when (val status = state.status) {
							is CarcassUiState.Status.Done -> stringResource(MR.strings.carcass_label_done, status.doneDailyDegrees)
							is CarcassUiState.Status.InProgress -> stringResource(
								MR.strings.carcass_label_daily_degrees,
								status.currentDailyDegrees.format(),
								(status.progress * 100).roundToInt(),
							)
						},
					)
				}

				(state.status as? CarcassUiState.Status.InProgress)?.let { status ->
					Spacer(Modifier.height(24.dp))

					LinearProgressIndicator(
						modifier = Modifier.fillMaxWidth(),
						progress = { status.progress },
					)
					Row(
						modifier = Modifier.fillMaxWidth(),
						horizontalArrangement = Arrangement.SpaceBetween,
					) {
						Text(
							text = stringResource(
								MR.strings.carcass_duration_ago_format,
								state.durationSinceStarted.format(),
							),
							style = MaterialTheme.typography.labelSmall,
							color = MaterialTheme.colorScheme.onSurfaceVariant,
						)
						Text(
							text = stringResource(
								MR.strings.carcass_duration_in_format,
								status.durationUntilDueEstimate.format(),
							),
							style = MaterialTheme.typography.labelSmall,
							color = MaterialTheme.colorScheme.onSurfaceVariant,
						)
					}
				}
			}

			var confirmDelete by remember { mutableStateOf(false) }
			if (confirmDelete) {
				AlertDialog(
					onDismissRequest = { confirmDelete = false },
					confirmButton = {
						TextButton(onClick = onDeleteClick) {
							Text(stringResource(MR.strings.button_delete))
						}
					},
					dismissButton = {
						TextButton(onClick = { confirmDelete = false }) {
							Text(stringResource(MR.strings.button_cancel))
						}
					},
					text = { Text(stringResource(MR.strings.carcass_label_confirm_delete, state.name)) },
				)
			}

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.End,
			) {
				IconButton(
					onClick = { confirmDelete = true },
				) {
					Icon(
						Icons.Default.DeleteOutline,
						contentDescription = stringResource(MR.strings.button_delete),
						tint = MaterialTheme.colorScheme.error,
					)
				}
				AnimatedVisibility(visible = state.status is CarcassUiState.Status.InProgress) {
					var showDatePicker by remember { mutableStateOf(false) }
					val datePickerState = rememberDatePickerState()
					var showTimePicker by remember { mutableStateOf(false) }
					val timePickerState = rememberTimePickerState()

					if (showDatePicker) {
						DatePickerDialog(
							onDismissRequest = { showDatePicker = false },
							confirmButton = {
								TextButton(
									onClick = {
										showDatePicker = false
										showTimePicker = true
									},
								) {
									Text(stringResource(MR.strings.button_ok))
								}
							},
							dismissButton = {
								TextButton(onClick = { showDatePicker = false }) {
									Text(stringResource(MR.strings.button_cancel))
								}
							},
						) {
							DatePicker(state = datePickerState)
						}
					}

					if (showTimePicker) {
						TimePickerDialog(
							onCancel = { showTimePicker = false },
							onConfirm = {
								datePickerState.selectedDateMillis?.let { selectedDateMillis ->
									onDoneClick(Instant.fromEpochMilliseconds(selectedDateMillis) + timePickerState.hour.hours + timePickerState.minute.minutes)
								}
							},
						) {
							TimePicker(state = timePickerState)
						}
					}

					IconButton(
						onClick = { showDatePicker = true },
					) {
						Icon(Icons.Default.Check, contentDescription = stringResource(MR.strings.carcasses_button_done))
					}
				}
			}
		}
	}
}

@Composable
private fun Duration.format(): String {
	return if (this == Duration.INFINITE) {
		stringResource(MR.strings.carcass_duration_in_unknown)
	} else toComponents { days, hours, _, _, _ ->
		stringResource(MR.strings.carcass_duration_short_format, days, hours)
	}
}

@Preview
@Composable
fun PreviewCarcass() {
	AppTheme {
		Carcass(
			state = CarcassUiState(
				id = 1L,
				name = "Name",
				durationSinceStarted = 2.days + 3.hours,
				status = CarcassUiState.Status.InProgress(
					durationUntilDueEstimate = 5.days,
					progress = 0.67f,
					currentDailyDegrees = 10.0,
				),
			),
			onDeleteClick = {},
			onEditClick = {},
			onDoneClick = {},
		)
	}
}
