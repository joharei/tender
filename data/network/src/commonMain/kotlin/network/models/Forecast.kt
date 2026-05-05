package network.models

import kotlinx.serialization.Serializable
import domain.models.Forecast as DomainForecast

@Serializable
internal data class Forecast(
	val hourly: Hourly
)

internal fun Forecast.asDomainModel() = DomainForecast(
	hourly = hourly.asDomainModel(),
)