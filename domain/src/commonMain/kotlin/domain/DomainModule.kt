package domain

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

public val domainModule: Module = module {
	factoryOf(::CalculateDueEstimateUseCase)
	factoryOf(::GetCarcassesUseCase)
	factoryOf(::GetCarcassUseCase)
	factoryOf(::AddCarcassUseCase)
	factoryOf(::DeleteCarcassUseCase)
	factoryOf(::UpdateCarcassUseCase)
	factoryOf(::MarkAsDoneUseCase)
}
