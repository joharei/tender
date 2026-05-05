package domain

import domain.repositories.CarcassRepository

public class DeleteCarcassUseCase(private val repo: CarcassRepository) {
	public suspend operator fun invoke(carcassId: Long) {
		repo.deleteCarcass(carcassId = carcassId)
	}
}
