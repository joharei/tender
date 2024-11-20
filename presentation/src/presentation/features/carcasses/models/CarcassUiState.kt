package presentation.features.carcasses.models

import kotlin.time.Duration

data class CarcassUiState(
	val id: Long,
	val name: String,
	val durationSinceStarted: Duration,
	val status: Status,
) {
	sealed interface Status {
		data class InProgress(
			val durationUntilDueEstimate: Duration,
			val progress: Float,
			val currentDailyDegrees: Double,
		) : Status

		data class Done(
			val doneDailyDegrees: Int,
		) : Status
	}
}