package domain

import domain.models.Carcass
import domain.repositories.CarcassRepository
import kotlinx.coroutines.flow.Flow

class GetCarcassUseCase(
	private val repo: CarcassRepository,
) {
	operator fun invoke(carcassId: Long): Flow<Carcass?> {
		return repo.carcass(carcassId)
	}
}
