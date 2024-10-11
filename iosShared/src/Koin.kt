package koin

import di.bridgeModule
import org.koin.core.context.startKoin
import presentation.di.presentationModule

fun initKoin() {
	startKoin {
		modules(bridgeModule, presentationModule)
	}
}