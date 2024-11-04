package shared.features.carcasses.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.features.carcasses.models.CarcassUiState
import presentation.utils.format
import resources.MR
import shared.ui.theme.AppTheme
import kotlin.math.roundToInt
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours

@Composable
fun Carcass(
	modifier: Modifier = Modifier,
	state: CarcassUiState,
	onDeleteClick: () -> Unit,
	onEditClick: () -> Unit,
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

				Text(
					text = stringResource(
						MR.strings.carcass_label_daily_degrees,
						state.current24HoursDegrees.format(),
						(state.progress * 100).roundToInt(),
					),
					style = MaterialTheme.typography.titleSmall,
				)

				Spacer(Modifier.height(24.dp))

				LinearProgressIndicator(
					modifier = Modifier.fillMaxWidth(),
					progress = { state.progress },
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
							state.durationUntilDueEstimate.format(),
						),
						style = MaterialTheme.typography.labelSmall,
						color = MaterialTheme.colorScheme.onSurfaceVariant,
					)
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

			IconButton(
				modifier = Modifier.align(Alignment.End),
				onClick = { confirmDelete = true },
			) {
				Icon(
					Icons.Default.DeleteOutline,
					contentDescription = stringResource(MR.strings.button_delete),
					tint = MaterialTheme.colorScheme.error,
				)
			}
		}
	}
}

@Composable
private fun Duration.format(): String {
	toComponents { days, hours, _, _, _ ->
		return stringResource(MR.strings.carcass_duration_short_format, days, hours)
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
				durationUntilDueEstimate = 5.days,
				progress = 0.67f,
				current24HoursDegrees = 10.0,
			),
			onDeleteClick = {},
			onEditClick = {},
		)
	}
}
