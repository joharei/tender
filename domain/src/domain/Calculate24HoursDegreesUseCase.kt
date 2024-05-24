package domain

import domain.repositories.ForecastRepository
import kotlinx.datetime.*
import org.koin.core.annotation.Factory

@Factory
class Calculate24HoursDegreesUseCase(private val forecastRepository: ForecastRepository) {
	suspend operator fun invoke(lat: Double, lon: Double, startDate: LocalDate): Double {
		val forecast = forecastRepository.getForecast(
			lat = lat,
			lon = lon,
			startDate = startDate,
			endDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
		)

		return forecast.hourly.temperature.sum() / 24
	}
}