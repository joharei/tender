package domain

import org.koin.dsl.module

val domainModule = module {
	factory { Calculate24HoursDegreesUseCase(get()) }
}