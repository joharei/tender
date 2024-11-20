package presentation.features.carcasses.models

data class CarcassesUiState(
	val activeCarcasses: List<CarcassUiState>,
	val doneCarcasses: List<CarcassUiState>,
	val loading: Boolean,
)