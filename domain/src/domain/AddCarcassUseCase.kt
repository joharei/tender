package domain

import domain.models.LatLon
import domain.repositories.CarcassRepository
import kotlinx.datetime.Instant

class AddCarcassUseCase(private val repo: CarcassRepository) {
	suspend operator fun invoke(name: String, startDate: Instant, location: LatLon) {
		repo.addCarcass(name = name, startDate = startDate, location = location)
	}
}