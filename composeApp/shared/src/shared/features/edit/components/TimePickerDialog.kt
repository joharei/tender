package shared.features.edit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import resources.MR
import shared.ui.theme.AppTheme

@Composable
fun TimePickerDialog(
	onCancel: () -> Unit,
	onConfirm: () -> Unit,
	toggle: @Composable () -> Unit = {},
	content: @Composable () -> Unit,
) {
	Dialog(
		onDismissRequest = onCancel,
		properties = DialogProperties(
			usePlatformDefaultWidth = false,
		),
	) {
		TimePickerDialogContent(
			title = stringResource(MR.strings.time_picker_dialog_select_time),
			onCancel = onCancel,
			onConfirm = onConfirm,
			toggle = toggle,
			content = content,
		)
	}
}

@Composable
private fun TimePickerDialogContent(
	title: String,
	onCancel: () -> Unit,
	onConfirm: () -> Unit,
	toggle: @Composable () -> Unit = {},
	content: @Composable () -> Unit,
) {
	Surface(
		shape = MaterialTheme.shapes.extraLarge,
		tonalElevation = 6.dp,
		modifier = Modifier
			.width(IntrinsicSize.Min)
			.height(IntrinsicSize.Min)
			.background(
				shape = MaterialTheme.shapes.extraLarge,
				color = MaterialTheme.colorScheme.surface,
			),
	) {
		Column(
			modifier = Modifier.padding(24.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Text(
				modifier = Modifier
					.fillMaxWidth()
					.padding(bottom = 20.dp),
				text = title,
				style = MaterialTheme.typography.labelMedium,
			)
			content()
			Row(
				modifier = Modifier
					.height(40.dp)
					.fillMaxWidth(),
			) {
				toggle()
				Spacer(modifier = Modifier.weight(1f))
				TextButton(
					onClick = onCancel,
				) { Text(stringResource(MR.strings.button_cancel)) }
				TextButton(
					onClick = onConfirm,
				) { Text(stringResource(MR.strings.button_ok)) }
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun Preview() {
	AppTheme {
		TimePickerDialogContent(
			onCancel = {},
			onConfirm = {},
			title = stringResource(MR.strings.time_picker_dialog_select_time),
		) {
			TimePicker(
				state = rememberTimePickerState(),
			)
		}
	}
}
