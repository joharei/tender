package domain

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
	factoryOf(::CalculateDueEstimateUseCase)
	factoryOf(::GetCarcassesUseCase)
	factoryOf(::AddCarcassUseCase)
}