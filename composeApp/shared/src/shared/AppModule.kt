package shared

import di.bridgeModule
import org.koin.dsl.module
import presentation.di.presentationModule

internal val appModule = module {
	includes(bridgeModule, presentationModule)
}