package network

import domain.models.Forecast
import domain.repositories.ForecastRepository
import kotlinx.datetime.LocalDate
import network.models.asDomainModel
import org.koin.core.annotation.Factory

@Factory
internal class ForecastRepositoryImpl(private val api: OpenMeteoApi) : ForecastRepository {
	override suspend fun getForecast(lat: Double, lon: Double, startDate: LocalDate, endDate: LocalDate): Forecast {
		return api.getForecast(
			lat = lat,
			lon = lon,
			startDate = startDate,
			endDate = endDate
		).asDomainModel()
	}
}