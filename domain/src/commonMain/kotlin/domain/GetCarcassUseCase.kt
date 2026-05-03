package domain

import domain.models.Carcass
import domain.repositories.CarcassRepository
import kotlinx.coroutines.flow.Flow

public class GetCarcassUseCase(
	private val repo: CarcassRepository,
) {
	public operator fun invoke(carcassId: Long): Flow<Carcass?> {
		return repo.carcass(carcassId)
	}
}
