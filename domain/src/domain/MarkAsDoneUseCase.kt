package domain

import domain.repositories.CarcassRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToInt

class MarkAsDoneUseCase(
	private val repo: CarcassRepository,
	private val calculateDueEstimateUseCase: CalculateDueEstimateUseCase,
) {
	suspend operator fun invoke(carcassId: Long, doneDate: Instant, currentDailyDegrees: Double) {
		val carcass = repo.carcass(carcassId).filterNotNull().first()
		repo.markAsDone(
			carcassId = carcassId,
			doneDate = doneDate,
			doneDailyDegrees = currentDailyDegrees.roundToInt(),
		)
	}
}