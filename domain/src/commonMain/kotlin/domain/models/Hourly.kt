package domain.models

import kotlinx.datetime.LocalDateTime

public data class Hourly(
	val time: List<LocalDateTime>,
	val temperature: List<Double>,
)
