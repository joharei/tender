package app.reitan.tender.addNew.models

import kotlinx.datetime.LocalDate

data class MainUiState(
	val location: LatLon? = null,
	val startDate: LocalDate? = null,
	val calculated24HoursDegrees: Double? = null,
)