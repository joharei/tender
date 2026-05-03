package presentation.features.carcasses.models

import kotlin.time.Duration

public data class CarcassUiState(
	val id: Long,
	val name: String,
	val durationSinceStarted: Duration,
	val status: Status,
) {
	public sealed interface Status {
		public data class InProgress(
			val durationUntilDueEstimate: Duration,
			val progress: Float,
			val currentDailyDegrees: Double,
		) : Status

		public data class Done(
			val doneDailyDegrees: Int,
		) : Status
	}
}
