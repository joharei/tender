package domain.models

import kotlinx.datetime.LocalDateTime

data class Hourly(
	val time: List<LocalDateTime>,
	val temperature: List<Double>,
)