package domain.repositories

import domain.models.Carcass
import domain.models.LatLon
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

interface CarcassRepository {
	fun carcasses(): Flow<List<Carcass>>
	suspend fun addCarcass(name: String, startDate: Instant, location: LatLon)
}