package domain.repositories

import kotlinx.datetime.LocalDate
import domain.models.Forecast

interface ForecastRepository {
	suspend fun getForecast(lat: Double, lon: Double, startDate: LocalDate, endDate: LocalDate): Forecast
}