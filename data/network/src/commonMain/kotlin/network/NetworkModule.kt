package network

import domain.repositories.ForecastRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

public val networkModule: Module = module {
	single { httpClient() }
	
	factoryOf(::OpenMeteoApi)

	factoryOf(::ForecastRepositoryImpl) bind ForecastRepository::class
}
