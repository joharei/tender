package domain.models

import kotlin.time.Instant

public data class DueEstimate(
	val currentDailyDegrees: Double,
	val dueEstimate: Instant,
)
