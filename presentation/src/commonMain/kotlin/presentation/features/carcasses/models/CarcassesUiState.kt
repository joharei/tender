package presentation.features.carcasses.models

public data class CarcassesUiState(
	val activeCarcasses: List<CarcassUiState>,
	val doneCarcasses: List<CarcassUiState>,
	val loading: Boolean,
)
