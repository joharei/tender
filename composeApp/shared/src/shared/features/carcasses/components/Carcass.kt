package shared.features.carcasses.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import shared.features.carcasses.models.CarcassUiState
import shared.utils.format
import kotlin.math.roundToInt
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours

@Composable
fun Carcass(
	modifier: Modifier = Modifier,
	state: CarcassUiState,
) {
	Card(modifier) {
		Column(
			modifier = Modifier.padding(16.dp),
		) {
			ProvideTextStyle(MaterialTheme.typography.headlineMedium) {
				Text(state.name)
			}

			ProvideTextStyle(MaterialTheme.typography.titleSmall) {
				Text("${state.current24HoursDegrees.format()} døgngrader (${(state.progress * 100).roundToInt()} %)")
			}

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
					text = "${state.durationSinceStarted.format()} siden",
					style = MaterialTheme.typography.labelSmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant,
				)
				Text(
					text = "Om ${state.durationUntilDueEstimate.format()}",
					style = MaterialTheme.typography.labelSmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant,
				)
			}
		}
	}
}

private fun Duration.format(): String {
	toComponents { days, hours, _, _, _ ->
		return "${days}d ${hours}h"
	}
}

@Preview
@Composable
fun PreviewCarcass() {
	MaterialTheme {
		Carcass(
			state = CarcassUiState(
				id = 1L,
				name = "Name",
				durationSinceStarted = 2.days + 3.hours,
				durationUntilDueEstimate = 5.days,
				progress = 0.67f,
				current24HoursDegrees = 10.0,
			),
		)
	}
}