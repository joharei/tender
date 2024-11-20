package db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneNotNull
import db.mappers.CarcassMapper
import domain.models.Carcass
import domain.models.LatLon
import domain.repositories.CarcassRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant

class CarcassRepositoryImpl(
	private val carcassQueries: CarcassQueries,
	private val carcassMapper: CarcassMapper,
) : CarcassRepository {
	override fun carcasses(): Flow<List<Carcass>> {
		return carcassQueries.selectAll()
			.asFlow()
			.mapToList(Dispatchers.IO)
			.map { carcasses ->
				carcasses.map(carcassMapper::invoke)
			}
	}

	override fun carcass(carcassId: Long): Flow<Carcass?> {
		return carcassQueries.getCarcass(carcassId)
			.asFlow()
			.mapToOneNotNull(Dispatchers.IO)
			.map(carcassMapper::invoke)
	}

	override suspend fun addCarcass(name: String, startDate: Instant, location: LatLon, dailyDegreesGoal: Int) =
		withContext(Dispatchers.IO) {
			carcassQueries.insert(
				name = name,
				start_date = startDate.toString(),
				location_lat = location.lat,
				location_lon = location.lon,
				daily_degrees_goal = dailyDegreesGoal,
			)
		}

	override suspend fun deleteCarcass(carcassId: Long) = withContext(Dispatchers.IO) {
		carcassQueries.delete(carcass_id = carcassId)
	}

	override suspend fun updateCarcass(carcass: Carcass) = withContext(Dispatchers.IO) {
		carcassQueries.update(
			carcass_id = carcass.id,
			name = carcass.name,
			start_date = carcass.startDate.toString(),
			location_lat = carcass.location.lat,
			location_lon = carcass.location.lon,
			daily_degrees_goal = carcass.dailyDegreesGoal,
		)
	}

	override suspend fun markAsDone(carcassId: Long, doneDate: Instant, doneDailyDegrees: Int) =
		withContext(Dispatchers.IO) {
			carcassQueries.markAsDone(
				carcass_id = carcassId,
				done_date = doneDate.toString(),
				done_daily_degrees = doneDailyDegrees,
			)
		}

}