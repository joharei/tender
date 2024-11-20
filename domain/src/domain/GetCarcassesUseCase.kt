package domain

import domain.models.CarcassWithEstimate
import domain.repositories.CarcassRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration

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
								val estimate = if (it.doneDate == null) {
									calculateDueEstimateUseCase(
										lat = it.location.lat,
										lon = it.location.lon,
										startDate = it.startDate.toLocalDateTime(TimeZone.currentSystemDefault()).date,
										dailyDegreesGoal = it.dailyDegreesGoal,
									)
								} else null
								CarcassWithEstimate(
									id = it.id,
									name = it.name,
									started = it.startDate,
									durationSinceStarted = (it.doneDate ?: Clock.System.now()) - it.startDate,
									status = if (estimate != null) {
										CarcassWithEstimate.Status.InProgress(
											durationUntilDueEstimate = if (estimate.dueEstimate == Instant.Companion.DISTANT_FUTURE) Duration.INFINITE else estimate.dueEstimate - Clock.System.now(),
											progress = (now.epochSeconds - it.startDate.epochSeconds).toFloat() / (estimate.dueEstimate.epochSeconds - it.startDate.epochSeconds),
											currentDailyDegrees = estimate.currentDailyDegrees,
										)
									} else {
										CarcassWithEstimate.Status.Done(doneDailyDegrees = it.doneDailyDegrees!!)
									},
									location = it.location,
								)
							}
						}
						.awaitAll()
				}
			}
	}
}
