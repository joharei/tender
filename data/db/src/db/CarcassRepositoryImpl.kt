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
						name = it.name,
						startDate = Instant.parse(it.start_date),
						location = LatLon(lat = it.location_lat, lon = it.location_lon),
					)
				}
			}
	}

	override suspend fun addCarcass(name: String, startDate: Instant, location: LatLon) = withContext(Dispatchers.IO) {
		carcassQueries.insert(
			name = name,
			start_date = startDate.toString(),
			location_lat = location.lat,
			location_lon = location.lon,
		)
	}

}