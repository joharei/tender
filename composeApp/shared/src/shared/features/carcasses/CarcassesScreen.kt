package shared.features.carcasses

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import shared.features.carcasses.models.CarcassesUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarcassesScreen(
	modifier: Modifier = Modifier,
	uiState: CarcassesUiState,
	onAddNewClick: () -> Unit,
) {
	Scaffold(
		modifier = modifier,
		topBar = {
			TopAppBar(title = { Text("Skrotter") })
		},
		floatingActionButton = {
			FloatingActionButton(onClick = onAddNewClick) {
				Icon(Icons.Default.Add, contentDescription = "Legg til ny skrott")
			}
		}
	) { contentPadding ->
		Crossfade(uiState.loading) {
			if (it) {
				Box(
					Modifier
						.padding(contentPadding)
						.fillMaxSize(),
					contentAlignment = Alignment.Center,
				) {
					CircularProgressIndicator()
				}
			} else {
				LazyColumn(contentPadding = contentPadding) {
					items(uiState.carcasses) {
						ListItem(headlineContent = { Text(it.name) })
					}
				}
			}
		}
	}
}

@Preview
@Composable
private fun Preview() {
	MaterialTheme {
		CarcassesScreen(
			uiState = CarcassesUiState(carcasses = emptyList(), loading = true),
			onAddNewClick = {},
		)
	}
}