package domain.models

import kotlinx.datetime.Instant
import kotlin.time.Duration

data class CarcassWithEstimate(
	val id: Long,
	val name: String,
	val started: Instant,
	val durationSinceStarted: Duration,
	val location: LatLon,
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