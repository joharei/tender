package domain

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
	factoryOf(::Calculate24HoursDegreesUseCase)
	factoryOf(::GetCarcassesUseCase)
	factoryOf(::AddCarcassUseCase)
}