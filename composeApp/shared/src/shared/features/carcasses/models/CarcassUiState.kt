package shared.features.carcasses.models

import kotlin.time.Duration

data class CarcassUiState(
	val id: Long,
	val name: String,
	val durationSinceStarted: Duration,
	val durationUntilDueEstimate: Duration,
	val progress: Float,
	val current24HoursDegrees: Double,
)