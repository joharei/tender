package domain

import domain.models.CarcassWithEstimate
import domain.repositories.CarcassRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetCarcassesUseCase(
	private val repo: CarcassRepository,
	private val calculateDueEstimateUseCase: CalculateDueEstimateUseCase,
) {
	operator fun invoke(): Flow<List<CarcassWithEstimate>> {
		val now = Clock.System.now()
		return repo.carcasses()
			.map { dbCarcasses ->
				coroutineScope {
					dbCarcasses
						.map {
							async {
								val estimate = calculateDueEstimateUseCase(
									lat = it.location.lat,
									lon = it.location.lon,
									startDate = it.startDate.toLocalDateTime(TimeZone.currentSystemDefault()).date,
								)
								CarcassWithEstimate(
									id = it.id,
									name = it.name,
									started = it.startDate,
									dueEstimate = estimate.dueEstimate,
									progress = (now.epochSeconds - it.startDate.epochSeconds).toFloat() / (estimate.dueEstimate.epochSeconds - it.startDate.epochSeconds),
									current24HoursDegrees = estimate.current24HoursDegrees,
									location = it.location,
								)
							}
						}
						.awaitAll()
				}
			}
	}
}
