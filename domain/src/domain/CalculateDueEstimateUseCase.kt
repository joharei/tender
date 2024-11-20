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
		val currentDailyDegrees = forecast.hourly.temperature
			.take(nowIndex)
			.sumOf { it.coerceAtLeast(0.0) } / 24

		var sum = .0
		val dueIndex = forecast.hourly.temperature.indexOfFirst {
			sum += it.coerceAtLeast(0.0)
			sum / 24 >= dailyDegreesGoal
		}
		val dueEstimate = if (dueIndex >= 0) {
			forecast.hourly.time[dueIndex].toInstant(TimeZone.UTC)
		} else {
			Instant.DISTANT_FUTURE
		}

		return DueEstimate(
			currentDailyDegrees = currentDailyDegrees,
			dueEstimate = dueEstimate,
		)
	}
}
