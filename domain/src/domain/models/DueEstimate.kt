package domain.models

import kotlinx.datetime.Instant

data class DueEstimate(
	val current24HoursDegrees: Double,
	val dueEstimate: Instant,
)