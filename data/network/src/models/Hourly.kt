package models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
	val time: List<LocalDateTime>,
	@SerialName("temperature_2m")
	val temperature: List<Double>,
)