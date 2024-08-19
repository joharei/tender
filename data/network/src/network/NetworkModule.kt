package network

import domain.repositories.ForecastRepository
import org.koin.dsl.module

val networkModule = module {
	single { httpClient() }
	factory { OpenMeteoApi(get()) }
	factory<ForecastRepository> { ForecastRepositoryImpl(get()) }
}