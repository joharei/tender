package domain.models

import kotlinx.datetime.Instant

data class Carcass(
	val id: Long,
	val startDate: Instant,
	val location: LatLon,
)