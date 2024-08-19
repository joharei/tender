package db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import domain.models.Carcass
import domain.models.LatLon
import domain.repositories.CarcassRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant

class CarcassRepositoryImpl(private val carcassQueries: CarcassQueries) : CarcassRepository {
	override fun carcasses(): Flow<List<Carcass>> {
		return carcassQueries.selectAll()
			.asFlow()
			.mapToList(Dispatchers.IO)
			.map { carcasses ->
				carcasses.map {
					Carcass(
						id = it.carcass_id,
						startDate = Instant.parse(it.start_date),
						location = LatLon(lat = it.location_lat, lon = it.location_lon),
					)
				}
			}
	}

	override suspend fun addCarcass(carcass: Carcass) = withContext(Dispatchers.IO) {
		carcassQueries.insert(
			start_date = carcass.startDate.toString(),
			location_lat = carcass.location.lat,
			location_lon = carcass.location.lon,
		)
	}

}