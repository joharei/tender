package shared.koinWorkaround

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.koin.compose.currentKoinScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

/*
    Ported directly from Android side. Waiting more feedback
 */

/**
 * Resolve ViewModel instance with Navigation NavBackStackEntry as extras parameters
 *
 * @param qualifier
 * @param parameters
 *
 * @author Arnaud Giuliani
 */
@OptIn(KoinInternalApi::class)
@KoinExperimentalAPI
@Composable
inline fun <reified T : ViewModel> koinNavViewModel(
	qualifier: Qualifier? = null,
	viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
		"No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
	},
	key: String? = null,
	extras: CreationExtras = defaultNavExtras(viewModelStoreOwner),
	scope: Scope = currentKoinScope(),
	noinline parameters: ParametersDefinition? = null,
): T {
	return resolveViewModel(
		T::class, viewModelStoreOwner.viewModelStore, key, extras, qualifier, scope, parameters,
	)
}