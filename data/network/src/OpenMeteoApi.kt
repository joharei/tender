import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.Json
import models.Forecast

class OpenMeteoApi {
	private val httpClient = HttpClient {
		install(ContentNegotiation) {
			json(Json {
				ignoreUnknownKeys = true
			})
		}
		install(Logging) {
			logger = object : Logger {
				override fun log(message: String) {
					Napier.v(tag = "HTTP Client", message = message)
				}
			}
			level = LogLevel.BODY
		}
	}.also { Napier.base(DebugAntilog()) }

	suspend fun getForecast(lat: Double, lon: Double, startDate: LocalDate, endDate: LocalDate): Forecast {
		return httpClient
			.get("https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lon&start_date=$startDate&end_date=$endDate&hourly=temperature_2m")
			.body()
	}
}