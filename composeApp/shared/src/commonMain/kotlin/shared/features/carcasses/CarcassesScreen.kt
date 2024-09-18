package shared.features.carcasses

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import shared.features.carcasses.components.Carcass
import shared.features.carcasses.models.CarcassUiState
import shared.features.carcasses.models.CarcassesUiState
import shared.ui.theme.AppTheme
import shared.utils.plus
import tender.composeapp.shared.generated.resources.Res
import tender.composeapp.shared.generated.resources.carcasses_button_add_new
import tender.composeapp.shared.generated.resources.carcasses_title
import kotlin.time.Duration.Companion.days

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarcassesScreen(
	modifier: Modifier = Modifier,
	uiState: CarcassesUiState,
	onAddNewClick: () -> Unit,
	onDeleteClick: (carcassId: Long) -> Unit,
	onEditClick: (carcassId: Long) -> Unit,
) {
	Scaffold(
		modifier = modifier,
		topBar = {
			CenterAlignedTopAppBar(title = { Text(stringResource(Res.string.carcasses_title)) })
		},
		floatingActionButton = {
			TooltipBox(
				positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
				tooltip = { PlainTooltip { Text(stringResource(Res.string.carcasses_button_add_new)) } },
				state = rememberTooltipState(),
			) {
				FloatingActionButton(onClick = onAddNewClick) {
					Icon(Icons.Default.Add, contentDescription = stringResource(Res.string.carcasses_button_add_new))
				}
			}
		},
	) { contentPadding ->
		Crossfade(
			targetState = uiState.loading,
		) {
			Box(Modifier.fillMaxSize()) {
				if (!it) {
					LazyVerticalGrid(
						modifier = Modifier
							.align(Alignment.TopCenter)
							.sizeIn(maxWidth = 800.dp),
						contentPadding = contentPadding + PaddingValues(16.dp),
						columns = GridCells.Adaptive(200.dp),
						horizontalArrangement = Arrangement.spacedBy(8.dp),
						verticalArrangement = Arrangement.spacedBy(8.dp),
					) {
						items(uiState.carcasses) {
							Carcass(
								state = it,
								onDeleteClick = { onDeleteClick(it.id) },
								onEditClick = { onEditClick(it.id) },
							)
						}
					}
				}
			}
		}
	}
}

@Preview
@Composable
private fun Preview() {
	AppTheme {
		CarcassesScreen(
			uiState = CarcassesUiState(
				carcasses = List(7) {
					CarcassUiState(
						id = it.toLong(),
						name = "Kjøtt $it",
						durationSinceStarted = it.days,
						durationUntilDueEstimate = it.days,
						progress = it / 7f,
						current24HoursDegrees = it * 10.0,
					)
				},
				loading = false,
			),
			onAddNewClick = {},
			onDeleteClick = {},
			onEditClick = {},
		)
	}
}
