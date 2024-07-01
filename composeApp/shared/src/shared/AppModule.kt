package shared

import domain.DomainModule
import network.NetworkModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [DomainModule::class, NetworkModule::class])
@ComponentScan
class AppModule