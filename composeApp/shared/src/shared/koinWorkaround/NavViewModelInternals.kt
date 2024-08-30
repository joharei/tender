package shared.koinWorkaround

import androidx.compose.runtime.Composable
import androidx.core.bundle.Bundle
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.navigation.NavBackStackEntry
import androidx.savedstate.SavedStateRegistryOwner
import org.koin.core.annotation.KoinInternalApi

/**
 * Resolve ViewModel instance
 *
 * @author Arnaud Giuliani
 */
@OptIn(KoinInternalApi::class)
@Composable
fun defaultNavExtras(viewModelStoreOwner: ViewModelStoreOwner): CreationExtras = when {
	//TODO To be fully verified
	viewModelStoreOwner is NavBackStackEntry && viewModelStoreOwner.arguments != null -> viewModelStoreOwner.arguments?.toExtras(
		viewModelStoreOwner,
	) ?: CreationExtras.Empty

	viewModelStoreOwner is HasDefaultViewModelProviderFactory -> viewModelStoreOwner.defaultViewModelCreationExtras
	else -> CreationExtras.Empty
}

/**
 * Convert current Bundle to CreationExtras
 * @param viewModelStoreOwner
 */
@KoinInternalApi
fun Bundle.toExtras(viewModelStoreOwner: ViewModelStoreOwner): CreationExtras? {
	return if (keySet().isEmpty()) null
	else {
		runCatching {
			MutableCreationExtras().also { extras ->
				extras[DEFAULT_ARGS_KEY] = this
				extras[VIEW_MODEL_STORE_OWNER_KEY] = viewModelStoreOwner
				extras[SAVED_STATE_REGISTRY_OWNER_KEY] = viewModelStoreOwner as SavedStateRegistryOwner
			}
		}.getOrNull()
	}
}