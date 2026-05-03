package domain.models

import kotlinx.datetime.Instant

public data class DueEstimate(
	val currentDailyDegrees: Double,
	val dueEstimate: Instant,
)
