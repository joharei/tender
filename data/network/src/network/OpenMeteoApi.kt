package network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.datetime.LocalDate
import network.models.Forecast

internal class OpenMeteoApi(private val httpClient: HttpClient) {
	suspend fun getForecast(lat: Double, lon: Double, startDate: LocalDate, endDate: LocalDate): Forecast {
		return httpClient
			.get("https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lon&start_date=$startDate&end_date=$endDate&hourly=temperature_2m")
			.body()
	}
}