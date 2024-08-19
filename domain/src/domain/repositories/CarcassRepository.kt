package domain.repositories

import domain.models.Carcass
import kotlinx.coroutines.flow.Flow

interface CarcassRepository {
	fun carcasses(): Flow<List<Carcass>>
	suspend fun addCarcass(carcass: Carcass)
}