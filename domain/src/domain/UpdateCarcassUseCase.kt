package domain

import domain.models.Carcass
import domain.repositories.CarcassRepository

class UpdateCarcassUseCase(private val repo: CarcassRepository) {
	suspend operator fun invoke(carcass: Carcass) {
		repo.updateCarcass(carcass)
	}
}