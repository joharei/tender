import kotlinx.coroutines.test.runTest
import kotlinx.datetime.*
import kotlin.test.Test
import kotlin.time.Duration.Companion.days

class OpenMeteoApiTest {
	@Test
	fun testApiCall() = runTest {
		val api = OpenMeteoApi()
		val now = Clock.System.now()
		val startDate = (now - 5.days)
			.toLocalDateTime(TimeZone.currentSystemDefault()).date
		val endDate = (now + 5.days)
			.toLocalDateTime(TimeZone.currentSystemDefault()).date
		val response = api.getForecast(lat = 62.3964924, lon = 6.5987279, startDate = startDate, endDate = endDate)
		print(response.hourly.temperature.sum() / 24)
	}
}