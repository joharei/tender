package network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import domain.models.Hourly as DomainHourly

@Serializable
internal data class Hourly(
	val time: List<LocalDateTime>,
	@SerialName("temperature_2m")
	val temperature: List<Double>,
)

internal fun Hourly.asDomainModel() = DomainHourly(
	time = time,
	temperature = temperature,
)