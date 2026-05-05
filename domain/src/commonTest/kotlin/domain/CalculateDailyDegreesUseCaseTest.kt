package domain

import domain.models.Forecast
import domain.models.Hourly
import domain.repositories.ForecastRepository
import io.mockative.Mock
import io.mockative.any
import io.mockative.classOf
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.hours

class CalculateDailyDegreesUseCaseTest {
	@Mock
	val mockRepository = mock(classOf<ForecastRepository>())
	private lateinit var subject: CalculateDueEstimateUseCase

	@BeforeTest
	fun setUp() {
		subject = CalculateDueEstimateUseCase(mockRepository)
	}

	@Test
	fun `GIVEN forecast WHEN called THEN return correct result`() = runTest {
		// GIVEN
		val stubForecast = Forecast(
			hourly = Hourly(
				time = List(48) {
					(Instant.parse("2024-03-10T00:00:00Z") + it.hours).toLocalDateTime(TimeZone.UTC)
				},
				temperature = List(48) { 20.0 },
			),
		)
		coEvery { mockRepository.getForecast(any(), any(), any(), any()) }
			.returns(stubForecast)
		val lat = 1.0
		val lon = 2.0
		val startDate = LocalDate(year = 2024, monthNumber = 3, dayOfMonth = 10)
		val endDate = startDate + DatePeriod(days = 14)

		// WHEN
		val result = subject(
			now = startDate.plus(DatePeriod(days = 1)).atStartOfDayIn(TimeZone.UTC),
			lat = lat,
			lon = lon,
			startDate = startDate,
			dailyDegreesGoal = 40
		)

		// Then
		assertEquals(expected = 20.0, actual = result.currentDailyDegrees)
		coVerify {
			mockRepository.getForecast(lat = lat, lon = lon, startDate = startDate, endDate = endDate)
			stubForecast.hourly.temperature
		}
	}

	@Test
	fun `GIVEN cold forecast WHEN called THEN return correct result`() = runTest {
		// GIVEN
		val stubForecast = Forecast(
			hourly = Hourly(
				time = List(48) {
					(Instant.parse("2024-11-20T00:00:00Z") + it.hours).toLocalDateTime(TimeZone.UTC)
				},
				temperature = List(48) { -1.0 },
			)
		)
		coEvery { mockRepository.getForecast(any(), any(), any(), any()) }
			.returns(stubForecast)
		val lat = 1.0
		val lon = 2.0
		val startDate = LocalDate(year = 2024, monthNumber = 11, dayOfMonth = 20)
		val endDate = startDate + DatePeriod(days = 14)

		// WHEN
		val result = subject(
			now = startDate.plus(DatePeriod(days = 1)).atStartOfDayIn(TimeZone.UTC),
			lat = lat,
			lon = lon,
			startDate = startDate,
			dailyDegreesGoal = 40
		)

		// Then
		assertEquals(expected = 0.0, actual = result.currentDailyDegrees)
		assertEquals(expected = Instant.DISTANT_FUTURE, result.dueEstimate)
		coVerify {
			mockRepository.getForecast(lat = lat, lon = lon, startDate = startDate, endDate = endDate)
			stubForecast.hourly.temperature
		}
	}
}