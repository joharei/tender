package shared.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowWidthSizeClass
import org.jetbrains.compose.resources.stringResource
import tender.composeapp.shared.generated.resources.Res
import tender.composeapp.shared.generated.resources.button_cancel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdaptiveAlertDialog(
	modifier: Modifier = Modifier,
	onDismissRequest: () -> Unit,
	title: @Composable () -> Unit,
	content: @Composable (contentPadding: PaddingValues) -> Unit,
) {
	val showAsDialog = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT

	if (showAsDialog) {
		AlertDialog(
			modifier = modifier,
			onDismissRequest = onDismissRequest,
			confirmButton = {},
			dismissButton = { TextButton(onClick = onDismissRequest) { Text(stringResource(Res.string.button_cancel)) } },
			title = title,
			text = { content(PaddingValues()) },
		)
	} else {
		Scaffold(
			modifier = modifier,
			topBar = {
				CenterAlignedTopAppBar(
					title = title,
					navigationIcon = {
						IconButton(onClick = onDismissRequest) {
							Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
						}
					},
				)
			},
			content = content,
		)
	}
}
