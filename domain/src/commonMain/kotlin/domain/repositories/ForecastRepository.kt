package domain.repositories

import kotlinx.datetime.LocalDate
import domain.models.Forecast

public interface ForecastRepository {
	public suspend fun getForecast(lat: Double, lon: Double, startDate: LocalDate, endDate: LocalDate): Forecast
}
