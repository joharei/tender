package domain

import domain.models.Forecast
import domain.models.Hourly
import domain.repositories.ForecastRepository
import io.mockative.*
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Calculate24HoursDegreesUseCaseTest {
	@Mock
	val mockRepository = mock(classOf<ForecastRepository>())
	private lateinit var subject: Calculate24HoursDegreesUseCase
	
	@BeforeTest
	fun setUp() {
		subject = Calculate24HoursDegreesUseCase(mockRepository)
	}
	
	@Test
	fun `GIVEN forecast WHEN called THEN return correct result`() = runTest {
		// GIVEN
		val stubForecast = Forecast(hourly = Hourly(time = emptyList(), temperature = List(24) { 4.0 }))
		coEvery { mockRepository.getForecast(any(), any(), any(), any()) }
			.returns(stubForecast)
		val lat = 1.0
		val lon = 2.0
		val startDate = LocalDate(year = 2024, monthNumber = 3, dayOfMonth = 10)
		val endDate = LocalDate(year = 2024, monthNumber = 3, dayOfMonth = 15)
		
		// WHEN
		val result = subject(lat = lat, lon = lon, startDate = startDate, endDate = endDate)
		
		// Then
		assertEquals(expected = 4.0, actual = result)
		coVerify {
			mockRepository.getForecast(lat = lat, lon = lon, startDate = startDate, endDate = endDate)
			stubForecast.hourly.temperature
		}
	}
}