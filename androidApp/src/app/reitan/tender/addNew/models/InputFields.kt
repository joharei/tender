package app.reitan.tender.addNew.models

import kotlinx.datetime.LocalDate

data class InputFields(
	val location: LatLon,
	val startDate: LocalDate,
)