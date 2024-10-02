package db.mappers

import domain.models.LatLon
import kotlinx.datetime.Instant
import db.Carcass as DbCarcass
import domain.models.Carcass as DomainCarcass

class CarcassMapper {
	operator fun invoke(dbModel: DbCarcass): DomainCarcass = DomainCarcass(
		id = dbModel.carcass_id,
		name = dbModel.name,
		startDate = Instant.parse(dbModel.start_date),
		location = LatLon(lat = dbModel.location_lat, lon = dbModel.location_lon),
		dailyDegreesGoal = dbModel.daily_degrees_goal,
		doneDate = dbModel.done_date?.let { Instant.parse(it) },
	)
}