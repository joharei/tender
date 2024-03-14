package domain

import domain.repositories.ForecastRepository
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Factory

@Factory
class Calculate24HoursDegreesUseCase(private val forecastRepository: ForecastRepository) {
	suspend operator fun invoke(lat: Double, lon: Double, startDate: LocalDate, endDate: LocalDate): Double {
		val forecast = forecastRepository.getForecast(
			lat = lat,
			lon = lon,
			startDate = startDate,
			endDate = endDate
		)

		return forecast.hourly.temperature.sum() / 24
	}
}