package app.reitan.tender.main.models

import kotlinx.datetime.LocalDate

data class MainUiState(
	val lat: String = "",
	val lon: String = "",
	val startDate: LocalDate? = null,
	val endDate: LocalDate? = null,
	val calculated24HoursDegrees: Double? = null,
)