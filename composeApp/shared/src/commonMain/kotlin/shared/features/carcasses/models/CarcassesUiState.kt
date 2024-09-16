package shared.features.carcasses.models

data class CarcassesUiState(
	val carcasses: List<CarcassUiState>,
	val loading: Boolean,
)