package shared.features.carcasses.models

import domain.models.Carcass

data class CarcassesUiState(
	val carcasses: List<Carcass>,
	val loading: Boolean,
)