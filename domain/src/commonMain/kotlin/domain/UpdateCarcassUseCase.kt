package domain

import domain.models.Carcass
import domain.repositories.CarcassRepository

public class UpdateCarcassUseCase(private val repo: CarcassRepository) {
	public suspend operator fun invoke(carcass: Carcass) {
		repo.updateCarcass(carcass)
	}
}
