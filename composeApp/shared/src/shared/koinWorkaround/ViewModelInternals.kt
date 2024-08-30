package shared.koinWorkaround

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.ParametersHolder
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope
import org.koin.mp.KoinPlatformTools
import kotlin.reflect.KClass

@KoinInternalApi
fun <T : ViewModel> resolveViewModel(
	vmClass: KClass<T>,
	viewModelStore: ViewModelStore,
	key: String?,
	extras: CreationExtras,
	qualifier: Qualifier?,
	scope: Scope,
	parameters: (() -> ParametersHolder)?,
): T {
	val factory = KoinViewModelFactory(vmClass, scope, qualifier, parameters)
	val provider = ViewModelProvider.create(viewModelStore, factory, extras)
	val vmKey = getViewModelKey(qualifier, key, KoinPlatformTools.getClassName(vmClass))
	return when {
		vmKey != null -> provider[vmKey, vmClass]
		else -> provider[vmClass]
	}
}

@KoinInternalApi
internal fun getViewModelKey(qualifier: Qualifier? = null, key: String? = null, className: String? = null): String? {
	return when {
		key != null -> key
		qualifier != null -> qualifier.value + (className?.let { "_$className" } ?: "")
		else -> null
	}
}

class KoinViewModelFactory(
	private val kClass: KClass<out ViewModel>,
	private val scope: Scope,
	private val qualifier: Qualifier? = null,
	private val params: ParametersDefinition? = null,
) : ViewModelProvider.Factory {

	//TODO Should handle Extras/Bundle injection here (see Android side)
	override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
		return scope.get(kClass, qualifier, params)
	}
}
