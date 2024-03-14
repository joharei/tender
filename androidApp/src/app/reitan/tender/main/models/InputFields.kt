package app.reitan.tender.main.models

import kotlinx.datetime.LocalDate

data class InputFields(
	val lat: Double,
	val lon: Double,
	val startDate: LocalDate,
	val endDate: LocalDate,
)