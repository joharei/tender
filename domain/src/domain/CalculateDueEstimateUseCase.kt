package domain

import domain.models.DueEstimate
import domain.repositories.ForecastRepository
import kotlinx.datetime.*
import kotlin.time.Duration.Companion.days

class CalculateDueEstimateUseCase(private val forecastRepository: ForecastRepository) {
	suspend operator fun invoke(
		now: Instant = Clock.System.now(),
		lat: Double,
		lon: Double,
		startDate: LocalDate,
		dailyDegreesGoal: Int,
	): DueEstimate {
		val forecast = forecastRepository.getForecast(
			lat = lat,
			lon = lon,
			startDate = startDate,
			endDate = now.plus(14.days).toLocalDateTime(TimeZone.UTC).date,
		)

		val nowLocalDateTime = now.toLocalDateTime(TimeZone.UTC)
		val nowIndex = forecast.hourly.time.indexOfFirst { it >= nowLocalDateTime }
		val current24HoursDegrees = forecast.hourly.temperature
			.take(nowIndex)
			.sum() / 24

		var sum = .0
		val dueIndex = forecast.hourly.temperature.indexOfFirst {
			sum += it
			sum / 24 >= dailyDegreesGoal
		}
		val dueEstimate = forecast.hourly.time[dueIndex]

		return DueEstimate(
			current24HoursDegrees = current24HoursDegrees,
			dueEstimate = dueEstimate.toInstant(TimeZone.UTC),
		)
	}
}
