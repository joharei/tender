package domain

import domain.repositories.CarcassRepository
import kotlin.math.roundToInt
import kotlin.time.Instant

public class MarkAsDoneUseCase(
	private val repo: CarcassRepository,
) {
	public suspend operator fun invoke(
		carcassId: Long,
		doneDate: Instant,
		currentDailyDegrees: Double,
	) {
		repo.markAsDone(
			carcassId = carcassId,
			doneDate = doneDate,
			doneDailyDegrees = currentDailyDegrees.roundToInt(),
		)
	}
}
