package domain.models

import kotlinx.datetime.Instant

data class CarcassWithEstimate(
	val id: Long,
	val name: String,
	val started: Instant,
	val dueEstimate: Instant,
	val progress: Float,
	val current24HoursDegrees: Double,
	val location: LatLon,
)