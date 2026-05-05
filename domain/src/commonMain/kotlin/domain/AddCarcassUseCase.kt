package domain

import domain.models.LatLon
import domain.repositories.CarcassRepository
import kotlin.time.Instant

public class AddCarcassUseCase(private val repo: CarcassRepository) {
	public suspend operator fun invoke(name: String, startDate: Instant, location: LatLon, dailyDegreesGoal: Int) {
		repo.addCarcass(name = name, startDate = startDate, location = location, dailyDegreesGoal = dailyDegreesGoal)
	}
}
