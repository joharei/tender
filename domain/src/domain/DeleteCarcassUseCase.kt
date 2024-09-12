package domain

import domain.models.Carcass
import domain.repositories.CarcassRepository

class DeleteCarcassUseCase(private val repo: CarcassRepository) {
	suspend operator fun invoke(carcassId: Long) {
		repo.deleteCarcass(carcassId = carcassId)
	}
}