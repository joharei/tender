package shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalViewConfiguration

@Composable
fun AdaptiveAlertDialog(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
	Box(
		Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background),
	) {
		content()
	}
}