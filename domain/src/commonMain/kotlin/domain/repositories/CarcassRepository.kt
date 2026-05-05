package domain.repositories

import domain.models.Carcass
import domain.models.LatLon
import kotlinx.coroutines.flow.Flow
import kotlin.time.Instant

public interface CarcassRepository {
	public fun carcasses(): Flow<List<Carcass>>
	public fun carcass(carcassId: Long): Flow<Carcass?>
	public suspend fun addCarcass(name: String, startDate: Instant, location: LatLon, dailyDegreesGoal: Int)
	public suspend fun deleteCarcass(carcassId: Long)
	public suspend fun updateCarcass(carcass: Carcass)
	public suspend fun markAsDone(carcassId: Long, doneDate: Instant, doneDailyDegrees: Int)
}
