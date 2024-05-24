package app.reitan.tender.addNew.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.*

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DatePickerBottomSheet(
	initialSelectedDate: LocalDate?,
	onDismissRequest: () -> Unit,
	onSetStartDate: (LocalDate) -> Unit,
) {
	val datePickerState = rememberDatePickerState(
		initialSelectedDateMillis = initialSelectedDate?.atStartOfDayIn(TimeZone.currentSystemDefault())
			?.toEpochMilliseconds(),
	)
	val confirmEnabled = datePickerState.selectedDateMillis != null
	ModalBottomSheet(
		onDismissRequest = onDismissRequest,
		sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 12.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
		) {
			IconButton(onClick = onDismissRequest) {
				Icon(Icons.Default.Close, contentDescription = "Cancel")
			}
			TextButton(
				onClick = {
					datePickerState.selectedDateMillis?.let {
						onSetStartDate(
							Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault()).date
						)
					}
					onDismissRequest()
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