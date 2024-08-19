package domain

import domain.models.Carcass
import domain.repositories.CarcassRepository
import kotlinx.coroutines.flow.Flow

class GetCarcassesUseCase(private val repo: CarcassRepository) {
	operator fun invoke(): Flow<List<Carcass>> {
		return repo.carcasses()
	}
}