package app.reitan.tender.addNew

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.reitan.tender.addNew.components.DatePickerBottomSheet
import app.reitan.tender.addNew.models.MainUiState
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewScreen(
	uiState: MainUiState,
	onNavigateUp: () -> Unit,
	onNavigateToMap: () -> Unit,
	onSetStartDate: (LocalDate) -> Unit,
) {
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text("Legg til kjøtt") },
				navigationIcon = {
					IconButton(onClick = onNavigateUp) {
						Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
					}
				}
			)
		},
	) { contentPadding ->
		Column(
			modifier = Modifier.padding(contentPadding),
			verticalArrangement = Arrangement.spacedBy(16.dp),
		) {
			ListItem(
				modifier = Modifier.clickable(onClick = onNavigateToMap),
				headlineContent = { Text("Plassering") },
				supportingContent = {
					Text(uiState.location?.let { (lat, lon) -> "${"%.6f".format(lat)}, ${"%.6f".format(lon)}" }
						?: "Velg plassering")
				},
				trailingContent = { Icon(Icons.AutoMirrored.Default.ArrowRight, contentDescription = null) },
			)

			var datePickerOpen by remember { mutableStateOf(false) }
			if (datePickerOpen) {
				DatePickerBottomSheet(
					initialSelectedDate = uiState.startDate,
					onDismissRequest = { datePickerOpen = false },
					onSetStartDate,
				)
			}
			ListItem(
				modifier = Modifier.clickable { datePickerOpen = true },
				headlineContent = { Text("Start") },
				supportingContent = { Text(uiState.startDate?.toString() ?: "Velg dato") },
				trailingContent = { Icon(Icons.AutoMirrored.Default.ArrowRight, contentDescription = null) },
			)

			AnimatedVisibility(uiState.calculated24HoursDegrees != null) {
				Text(
					modifier = Modifier.padding(horizontal = 16.dp),
					text = "Døgngrader: ${"%.0f".format(uiState.calculated24HoursDegrees)}",
				)
			}
		}
	}
}