package app.reitan.tender.addNew.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun LocationPicker(
	onNavigateToMap: () -> Unit,
	modifier: Modifier = Modifier,
) {
	val interactionSource = remember { MutableInteractionSource() }
	TextField(
		modifier = modifier
			.pointerInput(onNavigateToMap) {
				awaitEachGesture {
					// Must be PointerEventPass.Initial to observe events before the text field consumes them
					// in the Main pass
					awaitFirstDown(pass = PointerEventPass.Initial)
					val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
					if (upEvent != null) {
						onNavigateToMap()
					}
				}
			}
			.indication(interactionSource = interactionSource, indication = LocalIndication.current),
		interactionSource = interactionSource,
		value = "",
		onValueChange = {},
		readOnly = true,
		label = { Text("Posisjon") },
		trailingIcon = { Icon(Icons.AutoMirrored.Default.ArrowForward, null) },
	)
}