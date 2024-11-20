package shared.features.carcasses

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.features.carcasses.models.CarcassUiState
import presentation.features.carcasses.models.CarcassesUiState
import resources.MR
import shared.features.carcasses.components.Carcass
import shared.ui.theme.AppTheme
import shared.utils.plus
import kotlin.time.Duration.Companion.days

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarcassesScreen(
	modifier: Modifier = Modifier,
	uiState: CarcassesUiState,
	onAddNewClick: () -> Unit,
	onDeleteClick: (carcassId: Long) -> Unit,
	onEditClick: (carcassId: Long) -> Unit,
	onDoneClick: (carcassId: Long, doneDate: Instant, currentDailyDegrees: Double) -> Unit,
) {
	Scaffold(
		modifier = modifier,
		topBar = {
			CenterAlignedTopAppBar(title = { Text(stringResource(MR.strings.carcasses_title)) })
		},
		floatingActionButton = {
			TooltipBox(
				positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
				tooltip = { PlainTooltip { Text(stringResource(MR.strings.carcasses_button_add_new)) } },
				state = rememberTooltipState(),
			) {
				FloatingActionButton(onClick = onAddNewClick) {
					Icon(Icons.Default.Add, contentDescription = stringResource(MR.strings.carcasses_button_add_new))
				}
			}
		},
	) { contentPadding ->
		var showDone by remember { mutableStateOf(false) }

		Box(Modifier.fillMaxSize()) {
			LazyVerticalGrid(
				modifier = Modifier
					.align(Alignment.TopCenter)
					.sizeIn(maxWidth = 800.dp),
				contentPadding = contentPadding + PaddingValues(16.dp),
				columns = GridCells.Adaptive(200.dp),
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp),
			) {
				items(items = uiState.activeCarcasses, key = { it.id }) { carcass ->
					Carcass(
						modifier = Modifier.animateItem(),
						state = carcass,
						onDeleteClick = { onDeleteClick(carcass.id) },
						onEditClick = { onEditClick(carcass.id) },
						onDoneClick = {
							onDoneClick(
								carcass.id,
								it,
								(carcass.status as CarcassUiState.Status.InProgress).currentDailyDegrees,
							)
						},
					)
				}

				if (uiState.doneCarcasses.isNotEmpty()) {
					item(key = "expandButton", span = { GridItemSpan(maxLineSpan) }) {
						TextButton(
							modifier = Modifier
								.wrapContentWidth(align = Alignment.Start)
								.animateItem(),
							onClick = { showDone = !showDone },
							contentPadding = ButtonDefaults.TextButtonWithIconContentPadding,
						) {
							AnimatedContent(targetState = showDone) {
								Row {
									Icon(
										if (it) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
										contentDescription = null,
										modifier = Modifier.size(ButtonDefaults.IconSize),
									)
									Spacer(Modifier.size(ButtonDefaults.IconSpacing))
									Text(if (it) "Hide done" else "Show done")
								}
							}
						}
					}

					if (showDone) {
						items(items = uiState.doneCarcasses, key = { it.id }) { carcass ->
							Carcass(
								modifier = Modifier.animateItem(),
								state = carcass,
								onDeleteClick = { onDeleteClick(carcass.id) },
								onEditClick = { onEditClick(carcass.id) },
								onDoneClick = {
									onDoneClick(
										carcass.id,
										it,
										(carcass.status as CarcassUiState.Status.InProgress).currentDailyDegrees,
									)
								},
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
				activeCarcasses = List(7) {
					CarcassUiState(
						id = it.toLong(),
						name = "Kjøtt $it",
						durationSinceStarted = it.days,
						status = CarcassUiState.Status.InProgress(
							durationUntilDueEstimate = it.days,
							progress = it / 7f,
							currentDailyDegrees = it * 10.0,
						),
					)
				},
				doneCarcasses = emptyList(),
				loading = false,
			),
			onAddNewClick = {},
			onDeleteClick = {},
			onEditClick = {},
			onDoneClick = { _, _, _ -> },
		)
	}
}
