package domain

import domain.models.Carcass
import domain.repositories.CarcassRepository

class AddCarcassUseCase(private val repo: CarcassRepository) {
	suspend operator fun invoke(carcass: Carcass) {
		repo.addCarcass(carcass)
	}
}