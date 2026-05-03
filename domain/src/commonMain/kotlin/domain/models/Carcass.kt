package domain.models

import kotlinx.datetime.Instant

public data class Carcass(
	val id: Long,
	val name: String,
	val startDate: Instant,
	val location: LatLon,
	val dailyDegreesGoal: Int,
	val doneDate: Instant? = null,
	val doneDailyDegrees: Int? = null,
)
