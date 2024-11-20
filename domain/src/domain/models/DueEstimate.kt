package domain.models

import kotlinx.datetime.Instant

data class DueEstimate(
	val currentDailyDegrees: Double,
	val dueEstimate: Instant,
)